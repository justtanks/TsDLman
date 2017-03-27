package com.ts.databending.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/20.
 * 详情界面上传进展步骤的网络的请求数据
 */

public class XiangqingStepBean {

    /**
     * flag : Success
     * msg : [{"type_name":"商标已送报国家商标局","type_belong":[{"id":"1","section_belong_receipt":"1","section_name":"商标局受理申请"}]},
     * {"type_name":"形式审查中","type_belong":[{"id":"2","section_belong_receipt":"2","section_name":"商标符合要求，正在进入实质审查中"},
     * {"id":"3","section_belong_receipt":"2","section_name":"商标不符要求，请延期补正"}]},{"type_name":"实质审查中","type_belong":[{"id":"4","
     * section_belong_receipt":"3","section_name":"商标符合要求，正在初步审定公告"},{"id":"5","section_belong_receipt":"3","section_name":"商标全部/部分驳回申请
     * ，请申请复审"},{"id":"6","section_belong_receipt":"3","section_name":"复审申请失败，不予受理"},{"id":"7","section_belong_receipt":"3","section_name":"
     * 复审申请通过，商标评审委员会正在评审中"}]},{"type_name":"公告期","type_belong":[{"id":"8","section_belong_receipt":"4","section_name":"初步审定公告中"},{
     * "id":"9","section_belong_receipt":"4","section_name":"提出异议中"},{"id":"10","section_belong_receipt":"4","section_name":"异议理由成立，不予核准注册"},{
     * "id":"11","section_belong_receipt":"4","section_name":"提出异议复审中"},{"id":"12","section_belong_receipt":"4","section_name":"异议复审申请通过，
     * 商标评审委员会正在评审中"},{"id":"13","section_belong_receipt":"4","section_name":"异议理由不成立，正在进入核准注册中"}]},{
     * "type_name":"核准期","type_belong":[{"id":"14","section_belong_receipt":"5","section_name":"核准通过，正在下证中"},
     * {"id":"15","section_belong_receipt":"5","section_name":"注册商标有争议"},{"id":"16","section_belong_receipt":"5","section_name":"提出复审中"},
     * {"id":"17","section_belong_receipt":"5","section_name":"争议复审申请通过，商标评审委员会正在评审中"},{"id":"18","section_belong_receipt":"5",
     * "section_name":"存在争议，注册失败"}]},{"type_name":"收到注册证","type_belong":[{"id":"19","section_belong_receipt":"6","section_name":"下证中"}]}]
     * data : []
     * num : 1
     */

    private String flag;
    private int num;
    private List<MsgBean> msg;
    private List<?> data;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public List<MsgBean> getMsg() {
        return msg;
    }

    public void setMsg(List<MsgBean> msg) {
        this.msg = msg;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static class MsgBean {
        /**
         * type_name : 商标已送报国家商标局
         * type_belong : [{"id":"1","section_belong_receipt":"1","section_name":"商标局受理申请"}]
         */

        private String type_name;
        private List<TypeBelongBean> type_belong;

        public String getType_name() {
            return type_name;
        }

        public void setType_name(String type_name) {
            this.type_name = type_name;
        }

        public List<TypeBelongBean> getType_belong() {
            return type_belong;
        }

        public void setType_belong(List<TypeBelongBean> type_belong) {
            this.type_belong = type_belong;
        }

        public static class TypeBelongBean {
            /**
             * id : 1
             * section_belong_receipt : 1
             * section_name : 商标局受理申请
             */

            private int id;
            private int section_belong_receipt;
            private String section_name;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getSection_belong_receipt() {
                return section_belong_receipt;
            }

            public void setSection_belong_receipt(int section_belong_receipt) {
                this.section_belong_receipt = section_belong_receipt;
            }

            public String getSection_name() {
                return section_name;
            }

            public void setSection_name(String section_name) {
                this.section_name = section_name;
            }
        }
    }
}
