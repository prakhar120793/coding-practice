import java.util.Iterator;
import java.util.List;

public class NestedIterator implements Iterator<Integer> {

    public static class NestedInteger {
        private Integer integer;
        private List<NestedInteger> list;

        public boolean isInteger() {
            if (null == integer) {
                return false;
            }
            return true;
        }

        public Integer getInteger() {
            return integer;
        }

        public List<NestedInteger> getList() {
            return list;
        }
    }

    private int currentIndex;
    private List<NestedInteger> list;
    private NestedIterator child;

    public NestedIterator(List<NestedInteger> list) {
        this.list = list;
        this.child = null;
        this.currentIndex = 0;
        setChildIfRequired();
    }

    private void setChildIfRequired() {
        if (currentIndex < this.list.size() && !list.get(currentIndex).isInteger()) {
            this.child = new NestedIterator(this.list.get(currentIndex).getList());
            if(!this.child.hasNext()){
                currentIndex++;
                setChildIfRequired();
            }
        }
    }

    @Override
    public Integer next() {
        Integer integer;
        if (this.list.get(currentIndex).isInteger()) {
            integer = this.list.get(currentIndex++).getInteger();
            setChildIfRequired();
        } else {
            integer = this.child.next();
            if (!this.child.hasNext()) {
                currentIndex++;
                setChildIfRequired();
            }
        }
        return integer;
    }

    @Override
    public boolean hasNext() {
        if (this.currentIndex < this.list.size() - 1) {
            return true;
        }
        if (this.currentIndex >= this.list.size()) {
            return false;
        }
        if (this.list.get(this.currentIndex).isInteger()) {
            return true;
        }
        return this.child.hasNext();
    }

}
