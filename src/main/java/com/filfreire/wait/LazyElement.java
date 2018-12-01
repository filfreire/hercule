/*
 * Copyright (c) 2018 Filipe Freire
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.filfreire.wait;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

/**
 * FluentWait element abstraction.
 *
 * @since 1.0
 */
public class LazyElement {

    /**
     * Default millis value.
     */
    private static final int DEFAULT_MILLIS = 100;

    /**
     * Default seconds value.
     */
    private static final int DEFAULT_SECS = 5;

    /**
     * Webdriver.
     */
    private final WebDriver driver;

    /**
     * Selector.
     */
    private final By selector;

    /**
     * Ctor.
     *
     * @param driver Webdriver.
     * @param selector Selector.
     */
    public LazyElement(final WebDriver driver, final By selector) {
        this.driver = driver;
        this.selector = selector;
    }

    /**
     * Wait for element and return first element.
     *
     * @return WebElement.
     */
    public final WebElement first() {
        return new FluentWait<>(this.driver)
            .withTimeout(Duration.ofSeconds(this.DEFAULT_SECS))
            .pollingEvery(Duration.ofMillis(this.DEFAULT_MILLIS))
            .ignoring(NoSuchElementException.class)
            .until(
                ExpectedConditions.visibilityOfElementLocated(
                    this.selector
                )
            );
    }

    /**
     * Wait for and return list of elements.
     *
     * @return List of WebElement.
     */
    public final List<WebElement> list() {
        return new FluentWait<>(this.driver)
            .withTimeout(Duration.ofSeconds(this.DEFAULT_SECS))
            .pollingEvery(Duration.ofMillis(this.DEFAULT_MILLIS))
            .ignoring(NoSuchElementException.class)
            .until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(
                    this.selector
                )
            );
    }
}
