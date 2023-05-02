package com.craftinginterpreters.lox;

import java.util.List;
import java.util.Map;

public class LoxString extends LoxInstance {
    private final Map<String, LoxCallable> methods;

    String value;

    LoxString(String value) {
        super(null);
        this.value = value;
        this.methods = Map.of(
                "toUpperCase",
                new LoxCallable() {
                    @Override
                    public Object call(Interpreter interpreter,
                                       List<Object> arguments) {

                        return new LoxString(value.toUpperCase());
                    }

                    @Override
                    public int arity() {
                        return 0;
                    }
                }
        );
    }

    public LoxString add(LoxString other) {
        return new LoxString(value + other.value);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    Object get(Token name) {
        var method = methods.get(name.lexeme);
        if (method != null) {
            return method;
        }

        throw new RuntimeError(name, // [hidden]
                "Undefined property '" + name.lexeme + "'.");
    }

    @Override
    void set(Token name, Object value) {
        throw new RuntimeError(name, "Can't add properties to strings.");
    }

    static final LoxCallable string = new LoxCallable() {
        @Override
        public Object call(Interpreter interpreter,
                           List<Object> arguments) {
            return new LoxString(arguments.get(0).toString());
        }

        @Override
        public int arity() {
            return 1;
        }
    };
}
