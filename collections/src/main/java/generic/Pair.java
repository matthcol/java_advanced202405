package generic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.function.Function;

// @Data
// @AllArgsConstructor(staticName = "of")
@ToString
@EqualsAndHashCode
public class Pair<T,U> {
    private T first;
    private U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T,U> Pair<T,U> of(T first, U second){
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public <V,W> Pair<V, W> map(Function<T, V> functionFirst, Function<U, W> functionSecond) {
        var newFirst = functionFirst.apply(this.getFirst());
        var newSecond = functionSecond.apply(this.getSecond());
        return Pair.of(newFirst, newSecond);
    }


    // https://docs.oracle.com/javase/tutorial/java/generics/restrictions.html
    //public <E> void forbiddenUsage(E e){
    //    T t = (T) e; // with type erasure, dynamically T => Object
    //
    //    T t2 = new T();
    //
    //    T[] array = new T[10];
    //}
}
