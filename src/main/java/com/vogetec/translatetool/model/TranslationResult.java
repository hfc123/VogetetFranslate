package com.vogetec.translatetool.model;

import java.util.List;

public class TranslationResult implements ResultBean{
    private Result result;
    private long log_id;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    @Override
    public String getTranslateResultDst() {
        if (result!=null&& result.trans_result!=null&&result.trans_result.size()>0){
            String str="";
            for (int i = 0; i < result.trans_result.size(); i++) {
                str += result.trans_result.get(i).dst;
            }
            return str;
        }
        return null;
    }
    @Override
    public String getTranslateResultSrc() {
        if (result!=null&& result.trans_result!=null&&result.trans_result.size()>0){
            return result.trans_result.get(0).src;
        }
        return null;
    }
    // 内部类，表示翻译结果
    public static class Result {
        private String from;
        private List<TransResult> trans_result;
        private String to;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public List<TransResult> getTrans_result() {
            return trans_result;
        }

        public void setTrans_result(List<TransResult> trans_result) {
            this.trans_result = trans_result;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

    // 内部类，表示翻译结果中的每个单词
    public static class TransResult {
        private String src;
        private String dst;

        public String getSrc() {
            return src;
        }

        public void setSrc(String src) {
            this.src = src;
        }

        public String getDst() {
            return dst;
        }

        public void setDst(String dst) {
            this.dst = dst;
        }
    }
}
