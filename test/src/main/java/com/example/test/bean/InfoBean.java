package com.example.test.bean;

import java.util.List;

public class InfoBean {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : item1
         * select : true
         */

        private String name;
        private boolean select;
        private boolean sel;

        public boolean isSel() {
            return sel;
        }

        public void setSel(boolean sel) {
            this.sel = sel;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelect() {
            return select;
        }

        public void setSelect(boolean select) {
            this.select = select;
        }
    }
}
