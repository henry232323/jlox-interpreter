package com.craftinginterpreters.lox;

import com.craftinginterpreters.lox.Interpreter;
import com.craftinginterpreters.lox.LoxCallable;

import java.util.List;

public class Time {
    static final LoxCallable clock = new LoxCallable() {
        @Override
        public int arity() { return 0; }

        @Override
        public Object call(Interpreter interpreter,
                           List<Object> arguments) {
            return (double)System.currentTimeMillis() / 1000.0;
        }

        @Override
        public String toString() { return "<clock (native fn)>"; }
    };
}