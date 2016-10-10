/**
 * Copyright 2015-2016 The OpenZipkin Authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.weghst.konan.internal;


import java.util.Objects;

/**
 * Aids in converging streams which have 2-tuples, such as start/endTs and parent/spanId
 */
public final class Pair<T> {

    public static <T> Pair<T> create(T _1, T _2) {
        return new Pair<T>(_1, _2);
    }

    public final T _1;
    public final T _2;

    private Pair(T _1, T _2) {
        this._1 = Objects.requireNonNull(_1, "_1");
        this._2 = Objects.requireNonNull(_2, "_2");
    }

    @Override
    public String toString() {
        return "(" + _1 + ", " + _2 + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof Pair) {
            Pair that = (Pair) o;
            return this._1.equals(that._1) && this._2.equals(that._2);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int h = 1;
        h *= 1000003;
        h ^= this._1.hashCode();
        h *= 1000003;
        h ^= this._2.hashCode();
        return h;
    }
}
