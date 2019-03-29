/**
 *    Copyright 2019, Optimizely Inc. and contributors
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package com.optimizely.ab.processor;

import com.optimizely.ab.common.internal.Assert;

import javax.annotation.Nonnull;
import java.util.function.Consumer;

/**
 * An synchronous stage for behavior triggered on each input element to perform side-effects, such as mutation or
 * logging. Forwards each input element to the sink after invoking the behavior callback.
 *
 * @param <T> the type of input and output elements
 */
public class BehaviorStage<T> implements Stage<T, T> {
    private final Consumer<? super T> action;

    public BehaviorStage(Consumer<? super T> action) {
        this.action = Assert.notNull(action, "action");
    }

    @Nonnull
    @Override
    public Processor<T> getProcessor(@Nonnull Processor<? super T> sink) {
        return new BehaviorProcessor<>(sink, action);
    }
}