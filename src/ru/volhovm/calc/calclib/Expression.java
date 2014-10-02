package ru.volhovm.calc.calclib;

import ru.volhovm.calc.calclib.exceptions.CalcException;
import ru.volhovm.calc.calclib.numsystems.CalcNumerable;
import com.sun.istack.internal.NotNull;

/**
 * @author volhovm
 */

public interface Expression<T extends CalcNumerable> {
    @SuppressWarnings("unchecked")
    T evaluate(@NotNull T... args) throws CalcException;

    String toString();

    short getPriority();

    Expression<T> simplify(T type);

    boolean equals(Expression<T> a);
}
