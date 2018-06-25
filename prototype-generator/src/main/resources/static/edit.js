var declarationVue;
$(function () {

    $("#_deviceList").load("/modules/business/device/load/deviceList.html");

    declarationVue = new Vue({
        el: '#_edit',
        data: {
            applyC: T.p("applyC"),
            declaration: {},
            device: {},
            departmentOptions: [],  //部门下拉列表
            receiveUserOption: [],  //接收人列表
            applyUserOptions: [],   //申请人列表
        },
        mounted: function () {
            //初始化
            this.editPage();
            //初始化部门下拉
            this.getDepartments();
        },
        methods: {
            editPage: function () {
                if (this.applyC) {
                    $.ajax({
                        url: baseURL + 'serviceApply/' + this.applyC,
                        success: function (r) {
                            if (r.success) {
                                //TODO
                            }
                        }
                    })
                } else {
                    this.declaration = {
                        applyDeviceCodeC: '',
                        deviceInfo: '',
                        deviceName: '',
                        applyTypeC: '2',            //申报类型0: 技改1:维修 2:保养
                        implementPlaceTypeC: '1',   //实施地点 0:现场1:机修厂2:汽修厂
                        urgentTypeC: '1',           //紧急程度
                        implementPlaceM: '',        //故障地点
                        coordinate: '',             //设备故障坐标
                        applyDepartmentCodeC: '',        //申请部门
                        applyUserId: '',              //申请人
                        applyDt: moment(),        //申请时间
                        phenomenonDescX: [],            //现象描述
                        receiveDepartmentC: '',      //接收部门
                        receiveUserId: '',            //接收人
                        transferReasonX: '',                 //转件原因
                        filePathX: [],                 //上传的图片
                    }
                }
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
            openDeviceBox: function () {
                layer.open({
                    title: '选择设备信息',
                    type: 1,
                    area: ['95%', '98%'],
                    offset: 'auto',
                    content: $("#_deviceList"),
                    cancel: function (index) {
                        $("#_deviceList").hide();
                        layer.close(index);
                    }
                })
            },
            chooseDevice: function (device) {
                this.device = device;
                this.declaration.applyDeviceCodeC = device.deviceCode;
                this.declaration.deviceInfo = device.deviceCode + "+" + device.deviceName;
                $("#_deviceList").hide();
                layer.closeAll()
            },
            position: function () {
                layer.alert("因GIS地图未实现，此功能未开放");
            },
            getDepartmentUsers: function (department, type) {
                debugger;
                var _self = this;
                $.ajax({
                    url: baseURL + 'lookup/user/' + department,
                    success: function (r) {
                        debugger;
                        if (r.success) {
                            if (type == 1) {
                                //申请人
                                _self.applyUserOptions = r.data;
                            } else {
                                //移交人
                                _self.receiveUserOption = r.data;
                            }
                        }
                    }
                })
            },
            check: function (event) {
                this.applyTypeC = $(event.currentTarget).val();
            },
            submit: function () {
                
            },
            save: function () {
                if (!this.declaration.applyDeviceCodeC) {
                    layer.msg("请选择申报设备");
                    return;
                }

                if (!this.declaration.implementPlaceTypeC) {
                    layer.msg("请填写设备位置");
                    return;
                }

                var priceReg = /^[0-9A-Za-z\u4e00-\u9fa5]+$/;
                if (!priceReg.test(this.declaration.implementPlaceM)) {
                    layer.msg("设备位置只能输入数字英文与汉字");
                    return;
                }

                if (!this.declaration.applyDepartmentCodeC) {
                    layer.msg("请选择申请部门");
                    return;
                }

                // if (!this.declaration.applyUserId) {
                //     layer.msg("请选择申请人");
                //     return;
                // }

                if (!this.declaration.applyDt) {
                    layer.msg("请填写申请时间");
                    return;
                }

                if (this.declaration.phenomenonDescX.length == 0) {
                    layer.msg("请选择现象描述");
                    return;
                }

                if (this.declaration.filePathX.length == 0) {
                    layer.msg("请上传问题图片");
                    return;
                }

                this.declaration.applyDt = moment(this.declaration.applyDt).format("YYYY-MM-DD HH:mm:ss");
                this.declaration.phenomenonDescX = this.declaration.phenomenonDescX.join(',');
                this.declaration.filePathX = this.declaration.filePathX.join(',');
                if (this.applyC) {
                    //更新
                    this.put();
                } else {
                    //新增
                    this.post();
                }
            },
            post: function () {
                $.ajax({
                    url: baseURL + 'serviceApply',
                    type: 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(this.declaration),
                    success: function (r) {
                        if (r.success) {
                            layer.msg("新增完成");
                            window.location.href = "list.html";
                        }
                    }
                })
            },
            put: function () {
                $.ajax({
                    url: '',
                    type: 'put',
                    contentType: 'application/json',
                    data: JSON.stringify(this.declaration),
                    success: function (r) {
                        layer.msg("更新完成");
                    }
                })
            },
            uploadSuccess: function (r) {
                if (r.success) {
                    this.declaration.filePathX.push(r.data.httpFilePath)
                }
            },
            openBox: function () {
                $("#_uploadFile").val('');
                $("#_uploadFile").click();
            },
            upload: function () {
                $("#_uploadForm").submit();
            },
            removeImage: function (index) {
                this.declaration.filePathX.splice(index, 1);
            }
        }
    })
})
