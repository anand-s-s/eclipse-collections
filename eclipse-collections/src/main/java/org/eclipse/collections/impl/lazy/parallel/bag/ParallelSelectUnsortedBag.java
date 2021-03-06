/*
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.impl.lazy.parallel.bag;

import java.util.concurrent.ExecutorService;

import org.eclipse.collections.api.LazyIterable;
import org.eclipse.collections.api.annotation.Beta;
import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.primitive.ObjectIntProcedure;
import org.eclipse.collections.impl.block.factory.Predicates;
import org.eclipse.collections.impl.block.procedure.IfProcedure;

@Beta
class ParallelSelectUnsortedBag<T> extends AbstractParallelUnsortedBag<T, UnsortedBagBatch<T>>
{
    private final AbstractParallelUnsortedBag<T, ? extends UnsortedBagBatch<T>> parallelIterable;
    private final Predicate<? super T> predicate;

    ParallelSelectUnsortedBag(AbstractParallelUnsortedBag<T, ? extends UnsortedBagBatch<T>> parallelIterable, Predicate<? super T> predicate)
    {
        this.parallelIterable = parallelIterable;
        this.predicate = predicate;
    }

    @Override
    public ExecutorService getExecutorService()
    {
        return this.parallelIterable.getExecutorService();
    }

    @Override
    public int getBatchSize()
    {
        return this.parallelIterable.getBatchSize();
    }

    public void forEach(Procedure<? super T> procedure)
    {
        this.parallelIterable.forEach(new IfProcedure<T>(this.predicate, procedure));
    }

    public void forEachWithOccurrences(final ObjectIntProcedure<? super T> procedure)
    {
        this.parallelIterable.forEachWithOccurrences(new ObjectIntProcedure<T>()
        {
            public void value(T each, int parameter)
            {
                if (ParallelSelectUnsortedBag.this.predicate.accept(each))
                {
                    procedure.value(each, parameter);
                }
            }
        });
    }

    public boolean anySatisfy(Predicate<? super T> predicate)
    {
        return this.parallelIterable.anySatisfy(Predicates.and(this.predicate, predicate));
    }

    public boolean allSatisfy(Predicate<? super T> predicate)
    {
        return this.parallelIterable.allSatisfy(new SelectAllSatisfyPredicate<T>(this.predicate, predicate));
    }

    @Override
    public LazyIterable<UnsortedBagBatch<T>> split()
    {
        return this.parallelIterable.split().collect(new Function<UnsortedBagBatch<T>, UnsortedBagBatch<T>>()
        {
            public UnsortedBagBatch<T> valueOf(UnsortedBagBatch<T> eachBatch)
            {
                return eachBatch.select(ParallelSelectUnsortedBag.this.predicate);
            }
        });
    }

    public T detect(Predicate<? super T> predicate)
    {
        return this.parallelIterable.detect(Predicates.and(this.predicate, predicate));
    }

    private static final class SelectAllSatisfyPredicate<T> implements Predicate<T>
    {
        private final Predicate<? super T> left;
        private final Predicate<? super T> right;

        private SelectAllSatisfyPredicate(Predicate<? super T> left, Predicate<? super T> right)
        {
            this.left = left;
            this.right = right;
        }

        public boolean accept(T each)
        {
            boolean leftResult = this.left.accept(each);
            return !leftResult || this.right.accept(each);
        }
    }
}
