

$(document).ready(function() {
    $('#login').bootstrapValidator({
      //排除无需验证的控件，比如被禁用的或者被隐藏的  
        //submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面  
          
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },

    fields: {           
         username: {
          message: 'ID不合法',
          validators: {  
                    notEmpty: {//检测非空,radio也可用  
                        message: 'ID必须输入'  
                    },  
                    
                    stringLength: {//检测长度  
                        min: 6,  
                        max: 15,  
                        message: '长度必须在6-15之间'  
                    }
                }
            },
             password: {              
              validators: {  
                    notEmpty: {//检测非空,radio也可用  
                        message: '密码必须输入'  
                    }
                }
            },
        }
    });
});  
    $(document).ready(function() {
        $('#register').bootstrapValidator({
          //排除无需验证的控件，比如被禁用的或者被隐藏的  
            //submitButtons: '#btn-test',//指定提交按钮，如果验证失败则变成disabled，但我没试成功，反而加了这句话非submit按钮也会提交到action指定页面  
              
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },

        fields: {           
             id: {
              message: '用户名不合法',
              validators: {  
                        notEmpty: {//检测非空,radio也可用  
                            message: 'ID必须输入'  
                        },  
                        stringLength: {//检测长度  
                            min: 6,  
                            max: 15,  
                            message: '长度必须在6-15之间'  
                        }
                    }
                },
                 email: {              
                  validators: {  
                        notEmpty: {//检测非空,radio也可用  
                            message: '邮箱必须输入'  
                        },
                        emailAddress: {//验证email地址  
                            message: '不是正确的email地址'  
                        },                  
                    }
                },
                 name: {              
                    validators: {  
                          notEmpty: {//检测非空,radio也可用  
                              message: '姓名必须输入'  
                          }
                      }
                  },
                 password: {              
                      validators: {  
                            notEmpty: {//检测非空,radio也可用  
                                message: '密码必须输入'  
                            },
                      stringLength: {//检测长度  
                            min: 6,  
                            max: 15,  
                            message: '长度必须在6-15之间'  
                            },
                      different: {
                            	field:'id',
                            	message:'密码不能与ID相同'
                            }     
                        }
                    },
                
                  }                             
        });
       
    });  
    
    