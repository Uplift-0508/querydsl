/*
 * Copyright (c) 2010 Mysema Ltd.
 * All rights reserved.
 * 
 */
package com.mysema.query.types.expr;

import java.util.Arrays;
import java.util.List;

import com.mysema.query.types.Expr;
import com.mysema.query.types.Operation;
import com.mysema.query.types.Operator;
import com.mysema.query.types.Visitor;

/**
 * OSimple represents a simple operation expression
 * 
 * @author tiwe
 * 
 * @param <OpType>
 * @param <D>
 */
public class OSimple<OpType, D> extends ESimple<D> implements Operation<OpType, D> {
    
    private static final long serialVersionUID = -285668548371034230L;

    /**
     * Factory method
     * 
     * @param <OpType>
     * @param <D>
     * @param type
     * @param op
     * @param args
     * @return
     */
    public static <OpType,D> Expr<D> create(Class<? extends D> type, Operator<OpType> op, Expr<?>... args){
        return new OSimple<OpType,D>(type, op, args);
    }
    
    private final Operation<OpType, D> opMixin;

    OSimple(Class<? extends D> type, Operator<OpType> op, Expr<?>... args) {
        this(type, op, Arrays.asList(args));
    }

    OSimple(Class<? extends D> type, Operator<OpType> op, List<Expr<?>> args) {
        super(type);
        this.opMixin = new OperationMixin<OpType, D>(this, op, args);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);        
    }

    @Override
    public Expr<?> getArg(int index) {
        return opMixin.getArg(index);
    }

    @Override
    public List<Expr<?>> getArgs() {
        return opMixin.getArgs();
    }

    @Override
    public Operator<OpType> getOperator() {
        return opMixin.getOperator();
    }
    
    @Override
    public boolean equals(Object o){
        return opMixin.equals(o);
    }
    
    @Override
    public int hashCode(){
        return getType().hashCode();
    }
}