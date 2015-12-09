/*******************************************************************************
 * Copyright (c) 2015 Goldman Sachs.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *******************************************************************************/

package org.eclipse.collections.api.map;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.function.Function0;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.function.primitive.BooleanFunction;
import org.eclipse.collections.api.block.function.primitive.ByteFunction;
import org.eclipse.collections.api.block.function.primitive.CharFunction;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.api.block.function.primitive.FloatFunction;
import org.eclipse.collections.api.block.function.primitive.IntFunction;
import org.eclipse.collections.api.block.function.primitive.LongFunction;
import org.eclipse.collections.api.block.function.primitive.ShortFunction;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.block.procedure.Procedure2;
import org.eclipse.collections.api.list.ImmutableList;
import org.eclipse.collections.api.list.primitive.ImmutableBooleanList;
import org.eclipse.collections.api.list.primitive.ImmutableByteList;
import org.eclipse.collections.api.list.primitive.ImmutableCharList;
import org.eclipse.collections.api.list.primitive.ImmutableDoubleList;
import org.eclipse.collections.api.list.primitive.ImmutableFloatList;
import org.eclipse.collections.api.list.primitive.ImmutableIntList;
import org.eclipse.collections.api.list.primitive.ImmutableLongList;
import org.eclipse.collections.api.list.primitive.ImmutableShortList;
import org.eclipse.collections.api.multimap.list.ImmutableListMultimap;
import org.eclipse.collections.api.partition.list.PartitionImmutableList;
import org.eclipse.collections.api.tuple.Pair;

public interface ImmutableOrderedMap<K, V> extends OrderedMap<K, V>, ImmutableMapIterable<K, V>
{
    ImmutableOrderedMap<K, V> tap(Procedure<? super V> procedure);

    ImmutableOrderedMap<V, K> flipUniqueValues();

    ImmutableListMultimap<V, K> flip();

    ImmutableOrderedMap<K, V> select(Predicate2<? super K, ? super V> predicate);

    ImmutableOrderedMap<K, V> reject(Predicate2<? super K, ? super V> predicate);

    <K2, V2> ImmutableOrderedMap<K2, V2> collect(Function2<? super K, ? super V, Pair<K2, V2>> function);

    <R> ImmutableOrderedMap<K, R> collectValues(Function2<? super K, ? super V, ? extends R> function);

    ImmutableOrderedMap<K, V> toReversed();

    ImmutableOrderedMap<K, V> take(int count);

    ImmutableOrderedMap<K, V> takeWhile(Predicate<? super V> predicate);

    ImmutableOrderedMap<K, V> drop(int count);

    ImmutableOrderedMap<K, V> dropWhile(Predicate<? super V> predicate);

    PartitionImmutableList<V> partitionWhile(Predicate<? super V> predicate);

    ImmutableList<V> distinct();

    ImmutableList<V> select(Predicate<? super V> predicate);

    <P> ImmutableList<V> selectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    ImmutableList<V> reject(Predicate<? super V> predicate);

    <P> ImmutableList<V> rejectWith(Predicate2<? super V, ? super P> predicate, P parameter);

    PartitionImmutableList<V> partition(Predicate<? super V> predicate);

    <P> PartitionImmutableList<V> partitionWith(Predicate2<? super V, ? super P> predicate, P parameter);

    ImmutableBooleanList collectBoolean(BooleanFunction<? super V> booleanFunction);

    ImmutableByteList collectByte(ByteFunction<? super V> byteFunction);

    ImmutableCharList collectChar(CharFunction<? super V> charFunction);

    ImmutableDoubleList collectDouble(DoubleFunction<? super V> doubleFunction);

    ImmutableFloatList collectFloat(FloatFunction<? super V> floatFunction);

    ImmutableIntList collectInt(IntFunction<? super V> intFunction);

    ImmutableLongList collectLong(LongFunction<? super V> longFunction);

    ImmutableShortList collectShort(ShortFunction<? super V> shortFunction);

    <S> ImmutableList<Pair<V, S>> zip(Iterable<S> that);

    ImmutableList<Pair<V, Integer>> zipWithIndex();

    <P, V1> ImmutableList<V1> collectWith(Function2<? super V, ? super P, ? extends V1> function, P parameter);

    <V1> ImmutableList<V1> collectIf(Predicate<? super V> predicate, Function<? super V, ? extends V1> function);

    <S> ImmutableList<S> selectInstancesOf(Class<S> clazz);

    <V1> ImmutableList<V1> flatCollect(Function<? super V, ? extends Iterable<V1>> function);

    <V1> ImmutableListMultimap<V1, V> groupBy(Function<? super V, ? extends V1> function);

    <V1> ImmutableListMultimap<V1, V> groupByEach(Function<? super V, ? extends Iterable<V1>> function);

    <V1> ImmutableOrderedMap<V1, V> groupByUniqueKey(Function<? super V, ? extends V1> function);

    <KK, VV> ImmutableOrderedMap<KK, VV> aggregateInPlaceBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Procedure2<? super VV, ? super V> mutatingAggregator);

    <KK, VV> ImmutableOrderedMap<KK, VV> aggregateBy(Function<? super V, ? extends KK> groupBy, Function0<? extends VV> zeroValueFactory, Function2<? super VV, ? super V, ? extends VV> nonMutatingAggregator);
}