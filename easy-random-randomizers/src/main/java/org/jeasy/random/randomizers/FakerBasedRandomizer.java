/*
 * The MIT License
 *
 *   Copyright (c) 2020, Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 *
 *   Permission is hereby granted, free of charge, to any person obtaining a copy
 *   of this software and associated documentation files (the "Software"), to deal
 *   in the Software without restriction, including without limitation the rights
 *   to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *   copies of the Software, and to permit persons to whom the Software is
 *   furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in
 *   all copies or substantial portions of the Software.
 *
 *   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *   THE SOFTWARE.
 */
package org.jeasy.random.randomizers;

import io.github.serpro69.kfaker.Faker;
import io.github.serpro69.kfaker.FakerConfig;
import io.github.serpro69.kfaker.FakerConfigBuilder;
import org.jeasy.random.api.Randomizer;

import java.util.Locale;

/**
 * Abstract {@link Randomizer} based on <a href="https://github.com/DiUS/java-faker">Faker</a>.
 *
 * @param <T> the element type
 * @author Mahmoud Ben Hassine (mahmoud.benhassine@icloud.com)
 */
public abstract class FakerBasedRandomizer<T> extends AbstractRandomizer<T> {

    protected final Locale locale;
    protected final Faker faker;
    private FakerConfig.Builder configBuilder = FakerConfig.builder();

    protected FakerBasedRandomizer() {
        this.locale = Locale.ENGLISH;
        configBuilder.withLocale(locale.toString());
        faker = new Faker(configBuilder.build());
    }

    protected FakerBasedRandomizer(final long seed) {
        this(seed, Locale.ENGLISH);
    }

    protected FakerBasedRandomizer(final long seed, final Locale locale) {
        super(seed);
        this.locale = locale;
        if (locale == Locale.FRANCE || locale == Locale.JAPAN) {
            configBuilder.withLocale(locale.getLanguage());
        } else {
            configBuilder.withLocale(locale.toString());
        }
        configBuilder.withRandomSeed(seed);
        faker = new Faker(configBuilder.build());
    }
}
