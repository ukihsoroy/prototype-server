$(function () {

    new Vue({
        el: '#_list',
        data: {
            deviceCode: '',
            department: '',
            startTime: '',
            endTime: '',
            applyStatus: '',
            departmentOptions: [],
            rows: [],
            page: 1,
            limit: 10
        },
        mounted: function () {
            //初始化渲染分页
            this.pageRender();
            this.getDepartments();
        },
        methods: {
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
            query: function () {
                var _self = this;
                $.ajax({
                    url: baseURL + 'serviceApply',
                    data: {
                        deviceCode: this.deviceCode,
                        departmentCode: this.department,
                        startTime: this.startTime,
                        endTime: this.endTime,
                        applyStatus: this.applyStatus
                    },
                    success: function (r) {
                        if (r.success) {
                            _self.rows = r.data;
                        }
                    }
                })
            },
            formatUrgentTypeC: function (type) {
                var name = "";
                switch (type) {
                    case 0:
                        name = "技改";
                        break;
                    case 1:
                        name = "维修";
                        break;
                    case 2:
                        name = "保养";
                        break;
                    default:
                        break;
                }
                return name;
            },
            formatApplyStatus: function (status) {
                var name = "";
                switch (status) {
                    case 0:
                        name = "待处理";
                        break;
                    case 1:
                        name = "转交";
                        break;
                    case 2:
                        name = "已提交";
                        break;
                    case 3:
                        name = "已终止";
                        break;
                    default:
                        break;
                }
                return name;
            },
            add: function () {
                window.location.href = "/modules/business/device/declaration/edit.html";
            },
            exportExcel: function () {
                
            },
            edit: function (target) {
                if (target.status == 1) {
                    window.location.href = "/modules/business/device/declaration/edit.html?id=" + target.id;
                } else if (target.status == 2) {
                    window.location.href = "/modules/business/device/declaration/detail.html"
                        + "?id=" + target.id
                        + "&apply=1";   //转交
                }
            },
            detail: function (target) {
                window.location.href = "/modules/business/device/declaration/detail.html?id=" + target.id;
            },
            remove: function (target, index) {
                var _self = this;
                layer.confirm('确定删除?', function () {
                    $.ajax({
                        url: baseURL + '' + target.id,
                        type: 'delete',
                        success: function (r) {
                            if (r.success) {
                                //删除前台
                                _self.rows.splice(index, 1);
                            }
                        }
                    })
                })
            },
            stop: function (target) {
                var _self = this;
                layer.confirm('确定终止?', function () {
                    $.ajax({
                        url: baseURL + '' + target.id,
                        type: 'delete',
                        success: function (r) {
                            if (r.success) {
                                //删除前台
                                _self.rows.splice(index, 1);
                            }
                        }
                    })
                })
            },
            reason: function (target) {
                layer.alert("拒绝原因待定");
            },
            pageRender: function () {
                var _self = this;
                layui.laypage.render({
                    elem: 'layPager',
                    count: 2, //数据总数，从服务端得到
                    limit: 10,
                    theme: "#204d74",
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    jump: function(page){
                        _self.page = page.curr;
                        _self.limit = page.limit;
                        _self.query();
                    }
                });
            }
        }
    })

})