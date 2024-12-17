package com.vogetec.translatetool.http;

import java.util.List;

public class TranslationResponse {
    private Result result;
    private long log_id;

    public Result getResult() {
        return result;
    }

    public long getLogId() {
        return log_id;
    }

    public static class Result {
        private List<TransResult> trans_result;
        private String from;
        private String to;

        public List<TransResult> getTransResult() {
            return trans_result;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }
    }

    public static class TransResult {
        private String dst;
        private String src;

        public String getDst() {
            return dst;
        }

        public String getSrc() {
            return src;
        }
    }
}
