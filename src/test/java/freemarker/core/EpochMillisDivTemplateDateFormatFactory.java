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
package freemarker.core;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.lang.NotImplementedException;

import freemarker.template.TemplateDateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.utility.StringUtil;

public class EpochMillisDivTemplateDateFormatFactory extends TemplateDateFormatFactory {

    public static final EpochMillisDivTemplateDateFormatFactory INSTANCE = new EpochMillisDivTemplateDateFormatFactory();
    
    private EpochMillisDivTemplateDateFormatFactory() {
        // Defined to decrease visibility
    }
    
    @Override
    public TemplateDateFormat get(int dateType, boolean zonelessInput, String params, Locale locale, TimeZone timeZone,
            Environment env) throws TemplateModelException, UnknownDateTypeFormattingUnsupportedException,
                    InvalidFormatParametersException {
        int divisor;
        try {
            divisor = Integer.parseInt(params);
        } catch (NumberFormatException e) {
            if (params.length() == 0) {
                throw new InvalidFormatParametersException(
                        "A format parameter is required, which specifies the divisor.");
            }
            throw new InvalidFormatParametersException(
                    "The format paramter must be an integer, but was (shown quoted): " + StringUtil.jQuote(params));
        }
        return new EpochMillisDivTemplateDateFormat(divisor);
    }

    private static class EpochMillisDivTemplateDateFormat extends TemplateDateFormat {

        private final int divisor;
        
        private EpochMillisDivTemplateDateFormat(int divisor) {
            this.divisor = divisor;
        }
        
        @Override
        public String format(TemplateDateModel dateModel)
                throws UnformattableDateException, TemplateModelException {
            return String.valueOf(TemplateFormatUtil.getNonNullDate(dateModel).getTime() / divisor);
        }

        @Override
        public boolean isLocaleBound() {
            return false;
        }

        @Override
        public boolean isTimeZoneBound() {
            return false;
        }

        @Override
        public <MO extends TemplateMarkupOutputModel> MO format(TemplateDateModel dateModel,
                MarkupOutputFormat<MO> outputFormat) throws UnformattableNumberException, TemplateModelException {
            throw new NotImplementedException();
        }

        @Override
        public Date parse(String s) throws ParseException {
            try {
                return new Date(Long.parseLong(s));
            } catch (NumberFormatException e) {
                throw new ParseException("Malformed long", 0);
            }
        }

        @Override
        public String getDescription() {
            return "millis since the epoch";
        }
        
    }

}
