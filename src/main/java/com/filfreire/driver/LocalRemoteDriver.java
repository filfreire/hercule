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
package com.filfreire.driver;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

/**
 * Locally run Remote Driver interface.
 *
 * @since 1.0
 */
public class LocalRemoteDriver implements Driver {

    /**
     * Remote driver url.
     */
    private final String url;

    /**
     * Ctor.
     *
     * @param url Remote driver url.
     */
    public LocalRemoteDriver(final String url) {
        this.url = url;
    }

    @SuppressWarnings("PMD.AvoidThrowingRawExceptionTypes")
    @Override
    public final WebDriver instance() {
        final DesiredCapabilities capability = DesiredCapabilities.firefox();
        try {
            return new RemoteWebDriver(
                new URL(
                    new StringBuilder()
                        .append(this.url)
                        .append("/wd/hub")
                        .toString()
                ),
                capability
            );
        } catch (final MalformedURLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
