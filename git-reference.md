# Git Reference Guide

---

## Table of Contents

1. [Merge vs Rebase](#merge-vs-rebase)
2. [Worktrees](#worktrees)
3. [Multiple Git Profiles](#multiple-git-profiles)
4. [Handling Unversioned Files](#handling-unversioned-files)
5. [Lesser-Known But Important Commands](#lesser-known-but-important-commands)

---

## Merge vs Rebase

### The Analogy

**Merge** — Think of it like merging two highway lanes into one. Both roads existed separately, they come together at a junction, and there's a clear record of where they met. You can see the original paths.

**Rebase** — Think of it like packing up your suitcase from one hotel and re-checking into a new, better hotel — as if you had been there all along. Your changes are replayed on top of the latest base, history looks linear.

---

### Merge

Creates a **merge commit** that ties two branch histories together. The original branch history is preserved.

```
Before merge:
main:    A --- B --- C
                      \
feature:               D --- E

After: git merge feature (from main)
main:    A --- B --- C ------- M   ← merge commit
                      \       /
feature:               D --- E
```

```bash
git checkout main
git merge feature-branch

# With a custom message
git merge feature-branch -m "feat: merge user-auth feature into main"

# No fast-forward — always create a merge commit even if linear
git merge --no-ff feature-branch

# Abort an in-progress merge
git merge --abort
```

**When to use merge:**
- Public/shared branches where history should be preserved
- When you want an audit trail showing when features were integrated
- Team workflows on `main` or `develop`

---

### Rebase

**Replays** your commits on top of another branch tip. Creates new commit hashes. Produces a linear history.

```
Before rebase:
main:    A --- B --- C
                      \
feature:               D --- E

After: git rebase main (from feature)
main:    A --- B --- C
                      \
feature:               D' --- E'   ← new commits (replayed)
```

```bash
git checkout feature-branch
git rebase main

# Interactive rebase — rewrite last 3 commits
git rebase -i HEAD~3

# Interactive options inside the editor:
#   pick   → keep commit as-is
#   reword → keep commit, edit the message
#   squash → meld into previous commit
#   fixup  → like squash but discard this commit's message
#   drop   → delete this commit entirely

# Rebase onto a specific commit
git rebase --onto main feature-base feature-branch

# Abort a rebase in progress
git rebase --abort

# Continue after resolving conflicts
git rebase --continue
```

**When to use rebase:**
- Local feature branches before merging into main
- Keeping your branch up-to-date with main during development
- Cleaning up messy WIP commits before code review

---

### Golden Rule

> **Never rebase commits that have been pushed to a shared/public branch.**
> Rebase rewrites history (new SHA hashes). If teammates have pulled those commits,
> their history diverges from yours — causing painful conflicts.

---

### Merge vs Rebase: Quick Comparison

| Aspect             | Merge                          | Rebase                        |
|--------------------|--------------------------------|-------------------------------|
| History            | Preserves branch history       | Linear, clean history         |
| Merge commit       | Yes                            | No                            |
| Commit SHAs        | Unchanged                      | New SHAs created              |
| Conflict handling  | Once, at merge time            | Per commit being replayed     |
| Safe on shared?    | Yes                            | Only on local/private branches|
| Traceability       | Easy to see feature branches   | Harder to trace original work |

---

## Worktrees

A **worktree** lets you check out multiple branches simultaneously in separate directories — without stashing or switching branches. Each worktree shares the same `.git` directory.

**Use case:** You're deep in a feature, a hot-fix arrives, and you don't want to stash your work or lose context.

### Basic Usage

```bash
# Add a new worktree for a branch
git worktree add ../hotfix-dir hotfix/critical-bug

# Add a worktree and create a new branch at the same time
git worktree add -b feature/new-login ../login-feature main

# List all active worktrees
git worktree list

# Output:
# /home/user/my-repo         abc1234 [main]
# /home/user/hotfix-dir      def5678 [hotfix/critical-bug]
# /home/user/login-feature   ghi9012 [feature/new-login]
```

### Working in a Worktree

```bash
# Navigate to the worktree directory — it's a normal working directory
cd ../hotfix-dir
# Make changes, commit, push — all normal git operations work here
git add .
git commit -m "fix: resolve null pointer in payment flow"
git push origin hotfix/critical-bug

# Go back to your original work untouched
cd ../my-repo
```

### Removing a Worktree

```bash
# Remove a worktree (must be done from the main repo, not the worktree itself)
git worktree remove ../hotfix-dir

# Force remove if there are uncommitted changes
git worktree remove --force ../hotfix-dir

# Prune worktrees whose directories were manually deleted
git worktree prune
```

### Constraints

- You **cannot** check out the same branch in two worktrees at the same time.
- Worktrees share the object store — commits in one are immediately visible in another.
- The `.git` file inside a worktree is a pointer back to the main repo (not a full `.git` directory).

---

## Multiple Git Profiles

Useful when you have a **personal GitHub account** and a **work GitLab/GitHub account** and need different `user.name`, `user.email`, or SSH keys per project.

### Strategy: `includeIf` Directive

Git supports conditional config inclusion based on the directory path.

#### Global `~/.gitconfig`

```ini
[user]
    name = Rohit Prashar
    email = rohit@personal.com

# When inside ~/work/ directory tree, override with work profile
[includeIf "gitdir:~/work/"]
    path = ~/.gitconfig-work

# When inside ~/clients/acme/ directory tree
[includeIf "gitdir:~/clients/acme/"]
    path = ~/.gitconfig-acme
```

#### `~/.gitconfig-work`

```ini
[user]
    name = Rohit Prashar
    email = rohit.prashar@company.com

[core]
    sshCommand = ssh -i ~/.ssh/id_ed25519_work
```

#### `~/.gitconfig-acme`

```ini
[user]
    name = Rohit Prashar
    email = rohit@acme-client.com

[core]
    sshCommand = ssh -i ~/.ssh/id_ed25519_acme
```

### Verify Which Profile Is Active

```bash
# Check resolved config for the current repo
git config user.email
git config user.name

# Show where a config value is coming from
git config --show-origin user.email

# Output:
# file:/home/user/.gitconfig-work    rohit.prashar@company.com
```

### Per-Repo Override (One-off)

```bash
# Override locally for a single repo (writes to .git/config)
git config user.email "rohit@specificproject.com"
git config user.name "Rohit P"
```

### SSH Key Switching via `~/.ssh/config`

```
# ~/.ssh/config

# Personal GitHub
Host github-personal
    HostName github.com
    User git
    IdentityFile ~/.ssh/id_ed25519_personal

# Work GitHub
Host github-work
    HostName github.com
    User git
    IdentityFile ~/.ssh/id_ed25519_work
```

```bash
# Clone using the work identity
git clone git@github-work:org/repo.git

# Clone using personal identity
git clone git@github-personal:rohitprashar/project.git

# Update remote URL in existing repo to use the right host alias
git remote set-url origin git@github-work:org/repo.git
```

### Config Priority (Low to High)

```
system  (/etc/gitconfig)
  ↓
global  (~/.gitconfig)
  ↓
includeIf  (conditionally included file)
  ↓
local   (.git/config in the repo)    ← wins
```

---

## Handling Unversioned Files

### See What Is Untracked

```bash
# Show untracked files
git status

# Show only untracked filenames (scriptable)
git ls-files --others --exclude-standard
```

### `.gitignore` — Prevent Tracking

```bash
# .gitignore examples
*.log
*.class
target/
.env
.DS_Store
node_modules/
build/

# Negate — track this file even if parent pattern ignores it
!important.log

# Check why a file is being ignored
git check-ignore -v path/to/file.log
```

### Stop Tracking a File That's Already Committed

```bash
# Remove from index (stop tracking) but keep the file on disk
git rm --cached path/to/secret.env

# Stop tracking an entire directory
git rm -r --cached target/

# Then commit the removal
git add .gitignore
git commit -m "chore: stop tracking build artifacts"
```

### Delete Untracked Files — `git clean`

```bash
# Preview what would be deleted (dry run — always do this first)
git clean -n

# Delete untracked files
git clean -f

# Delete untracked files AND directories
git clean -fd

# Delete untracked files + ignored files (nuclear option)
git clean -fdx

# Interactive mode — choose what to delete
git clean -i
```

### Temporarily Hide Changes — `git stash`

```bash
# Stash current changes (tracked files)
git stash

# Stash with a descriptive name
git stash push -m "WIP: login form validation"

# Include untracked files in stash
git stash push -u -m "WIP: login form with new assets"

# Include ignored files too
git stash push -a

# List stashes
git stash list
# stash@{0}: WIP: login form validation
# stash@{1}: WIP: payment refactor

# Apply a specific stash and keep it in the list
git stash apply stash@{1}

# Apply the latest stash and drop it from the list
git stash pop

# Drop a specific stash
git stash drop stash@{0}

# See what's in a stash before applying
git stash show -p stash@{0}
```

---

## Lesser-Known But Important Commands

### `git reflog` — Recover "Lost" Commits

The reflog records every position HEAD has been at. Use it to recover after a bad reset or accidental branch delete.

```bash
git reflog

# Output:
# abc1234 HEAD@{0}: reset: moving to HEAD~2
# def5678 HEAD@{1}: commit: feat: add payment flow
# ghi9012 HEAD@{2}: commit: fix: null check

# Recover the "lost" commit
git checkout def5678
# or restore a branch to that point
git branch recovery-branch def5678
```

---

### `git bisect` — Binary Search for a Bug

When you know a bug exists now but didn't exist 100 commits ago, bisect finds the exact commit that introduced it.

```bash
git bisect start

# Tell git the current state is broken
git bisect bad

# Tell git a known good commit (tag, SHA, or relative ref)
git bisect good v2.1.0

# Git checks out a middle commit — test your code, then tell git:
git bisect good   # if this commit is fine
git bisect bad    # if this commit has the bug

# Git keeps halving until it pinpoints the bad commit
# When done:
git bisect reset  # return to original HEAD

# Automate with a test script (exit 0 = good, exit 1 = bad)
git bisect run ./test-script.sh
```

---

### `git cherry-pick` — Apply a Specific Commit

```bash
# Apply commit abc1234 onto the current branch
git cherry-pick abc1234

# Cherry-pick a range of commits
git cherry-pick abc1234..def5678

# Cherry-pick without auto-committing (stage only)
git cherry-pick -n abc1234

# If there are conflicts, resolve then:
git cherry-pick --continue
# or bail out:
git cherry-pick --abort
```

---

### `git reset` vs `git restore` vs `git revert`

| Command        | What it does                                        | Changes history? |
|----------------|-----------------------------------------------------|------------------|
| `git reset`    | Moves HEAD (and optionally index/working tree)      | Yes (local only) |
| `git restore`  | Restores files in working tree or index             | No               |
| `git revert`   | Creates a new commit that undoes a previous commit  | No (safe)        |

```bash
# --- git reset ---

# Undo last commit, keep changes staged
git reset --soft HEAD~1

# Undo last commit, keep changes unstaged (default)
git reset --mixed HEAD~1

# Undo last commit, DISCARD all changes (destructive)
git reset --hard HEAD~1

# --- git restore ---

# Discard unstaged changes to a file
git restore path/to/file.java

# Unstage a file (move from index back to working tree)
git restore --staged path/to/file.java

# Restore a file from a specific commit
git restore --source abc1234 path/to/file.java

# --- git revert ---

# Create a new commit that undoes commit abc1234 (safe for shared branches)
git revert abc1234

# Revert without auto-committing
git revert -n abc1234
```

---

### `git commit --fixup` + `git rebase --autosquash`

Clean up a messy commit without manually editing the rebase todo list.

```bash
# You have a typo in commit abc1234 — make the fix, then:
git add path/to/fixed-file.java
git commit --fixup abc1234
# Creates a commit: "fixup! original commit message"

# Now rebase to automatically squash it in
git rebase -i --autosquash HEAD~5
# Git arranges the fixup! commit right after abc1234 automatically
```

---

### `git log` — Useful Variants

```bash
# Compact graph view of all branches
git log --oneline --graph --decorate --all

# Who changed what in a file
git log --follow -p -- path/to/file.java

# Commits by a specific author
git log --author="Rohit" --oneline

# Commits within a date range
git log --after="2024-01-01" --before="2024-06-01" --oneline

# Search commit messages
git log --grep="payment" --oneline

# Find commits that added/removed a specific string in code
git log -S "calculateTotal" --oneline

# Summarize commits by author
git shortlog -sn
```

---

### `git diff` — Useful Variants

```bash
# Diff of staged changes (what will be committed)
git diff --staged

# Diff between two branches
git diff main..feature-branch

# Show only the names of changed files
git diff --name-only main..feature-branch

# Show names and change status (M=modified, A=added, D=deleted)
git diff --name-status main..feature-branch

# Word-level diff (easier to read for prose/config changes)
git diff --word-diff
```

---

### `git blame` — Who Wrote This Line?

```bash
# Show who last modified each line
git blame path/to/file.java

# Show blame for a specific line range
git blame -L 42,60 path/to/file.java

# Ignore whitespace changes
git blame -w path/to/file.java

# Show the commit that removed a line (requires git log -S)
git log -S "the deleted line" -- path/to/file.java
```

---

### `git tag` — Versioning

```bash
# Create a lightweight tag
git tag v1.0.0

# Create an annotated tag (recommended — includes author, date, message)
git tag -a v1.0.0 -m "Release version 1.0.0"

# Tag a specific past commit
git tag -a v0.9.0 abc1234 -m "Beta release"

# Push tags to remote (tags are not pushed by default)
git push origin v1.0.0
git push origin --tags   # push all tags

# Delete a tag locally and remotely
git tag -d v1.0.0
git push origin --delete v1.0.0
```

---

### `git archive` — Export Source Without `.git`

```bash
# Export HEAD as a zip (useful for deployments or sharing)
git archive --format=zip HEAD > release.zip

# Export a specific tag
git archive --format=tar.gz v1.0.0 > release-v1.0.0.tar.gz

# Export only a subdirectory
git archive HEAD src/ > src-only.zip
```

---

### Useful Aliases to Add to `~/.gitconfig`

```ini
[alias]
    lg     = log --oneline --graph --decorate --all
    st     = status -sb
    unstage = restore --staged
    undo   = reset --soft HEAD~1
    aliases = config --get-regexp alias
    wip    = !git add -A && git commit -m "WIP"
    unwip  = reset HEAD~1 --mixed
```

```bash
# After adding aliases, use them:
git lg        # pretty graph log
git undo      # undo last commit, keep changes staged
git wip       # quick save everything as WIP
git unwip     # undo the WIP commit
```
