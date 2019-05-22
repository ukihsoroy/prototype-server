$(function () {
    new Vue({
        el: '#_detail',
        data: {
            receiveDepartment: '',  //接收部门
            departmentOptions: [],
            receiveUser: '',        //接收人
            receiveUserOption: [],
            reason: '',             //转件原因
            images: [],             //上传的图片
            apply: T.p('apply')
        },
        mounted: function () {
            this.getDepartments();
        },
        methods: {
            formatType: function (type) {
                var name;
                switch (type) {
                    case 1:
                        name = "技改实施";
                        break;
                    case 2:
                        name = "故障维修";
                        break;
                    case 3:
                        name = "设备保养";
                        break;
                    default:
                        name = "";
                        break;
                }
                return name;
            },
            getDepartments: function () {
                var _self = this;
                $.ajax({
                    url: baseURL + "/lookup/org",
                    success: function (r) {
                        if (r.success) {
                            _self.departmentOptions = r.data;
                        }
                    }
                })
            },
            submit: function () {

            },
            save: function () {

            },
        },
        watch: {
            receiveDepartment: function (n, o) {
                var _self = this;
                $.ajax({
                    url: baseURL + 'lookup/user/' + n,
                    success: function (r) {
                        if (r.success) {
                            //移交人
                            _self.receiveUserOption = r.data;
                        }
                    }
                })
            }
        }
    })
})