--Window functions perform aggreagtion or operations on groups of rows
-- but they produce result for each row

--
select * from employees
emp_id , department salary


-- find the average saleary for each department
SELECT
    emp_id,
    name,
    department,
    salary,
    AVG(salary) OVER (PARTITION BY department) AS dept_average_salary,
        min(salary) over (partition by department) as min_salary,
        max(salary) over (partition by department) as max_salary,
        rank() over (PARTITION BY department order by salary desc) as salary_rank
FROM employees;

Window Function Behavior:

PARTITION BY:  is similar to group by

The RANK() function ranks rows within a specific window or group defined by the PARTITION BY clause.
In this context, it assigns a rank to each employee's salary within their department, ordered by salary (descending).
/**
employees Table:
emp_id	    name	    department	    salary
1	        Alice	    Engineering	    75000
2	        Bob	        Engineering	    80000
3	        Charlie	    Sales	        55000
4	        Diana	    Sales	        50000
5	        Eve	        Sales	        55000
6	        Frank	    Engineering	    75000

SELECT
    emp_id,
    name,
    department,
    salary,
    RANK() OVER (PARTITION BY department ORDER BY salary DESC) AS salary_rank
FROM employees;

emp_id	name	department	    salary	salary_rank
2	    Bob	    Engineering	    80000	1
1	    Alice	Engineering	    75000	2
6	    Frank	Engineering	    75000	2
3	    Charlie	Sales	        55000	1
5	    Eve	    Sales	        55000	1
4	    Diana	Sales	        50000	3


