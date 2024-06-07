package collections.list;

import java.util.ArrayList;
import java.util.List;

public class CustomArrayList extends ArrayList {

    @Override
    public boolean add(final Object o) {
        if(this.contains(o))
            return true;
        else
        {
          return   super.add(o);
        }
    }
}
