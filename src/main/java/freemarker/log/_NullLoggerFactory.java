/*
 * Copyright 2014 Attila Szegedi, Daniel Dekany, Jonathan Revusky
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package freemarker.log;

/**
 * Don't use this class; it's only public to work around Google App Engine Java
 * compliance issues. FreeMarker developers only: treat this class as package-visible.
 */
public class _NullLoggerFactory implements LoggerFactory {
    _NullLoggerFactory()
    {
    }
    
    public Logger getLogger(String category) {
        return INSTANCE;
    }

    private static final Logger INSTANCE = new Logger()
    {
        public void debug(String message) {
        }

        public void debug(String message, Throwable t) {
        }

        public void error(String message) {
        }

        public void error(String message, Throwable t) {
        }

        public void info(String message) {
        }

        public void info(String message, Throwable t) {
        }

        public void warn(String message) {
        }

        public void warn(String message, Throwable t) {
        }

        public boolean isDebugEnabled() {
            return false;
        }

        public boolean isInfoEnabled() {
            return false;
        }

        public boolean isWarnEnabled() {
            return false;
        }

        public boolean isErrorEnabled() {
            return false;
        }

        public boolean isFatalEnabled() {
            return false;
        }
    };
}
