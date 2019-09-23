
axios.defaults.withCredentials=true;
var school = new Vue({
				el:".main",
				data:{
					modelflag:1,
					ip:"192.168.0.105:8080",
                    tarip:"192.168.0.105:8080"
				},
				created() {
					modelchange_login.style.background="#d9534f";
				},
				methods:{
					change:function(e){
						this.modelflag=e;
						if(e){
							model_login.style.display="block";
							model_zhuce.style.display="none";
							modelchange_login.style.background="#d9534f"
							modelchange_zhuce.style.background="#C0C0C0"
						}else{
							model_login.style.display="none";
							model_zhuce.style.display="block";
							modelchange_login.style.background="#C0C0C0"
							modelchange_zhuce.style.background="#d9534f"
						}
					},
					send:function(){
						let _this = this;
						if(this.modelflag){
							let dom = document.getElementsByClassName("l");
							let data = {};
							data.type = "login";
							data.data = {};
							data.data.user = dom[0].value;
							data.data.pass = dom[1].value;
							var url = "";
							var type = 1;
							if(dom[0].value=="admin"){
								url = "http://"+_this.tarip+"/backend/client/admin_login";
								type = 0;
							}else{
								url = "http://"+_this.tarip+"/portal/client/login";
							}
								 axios.get(url,{
									params:{
										username:dom[0].value,
										password:dom[1].value
									}
								}).then(res=>{
									let data = res.data;
							          console.log(data)
							if(data.status==0){
								self.location.href="http://"+_this.ip+"/main-manager/main.html?type="+type;
							}else if(data.status==1){
								alert("账号或密码错误");
							}else if(data.status==2){
								alert("非法参数");
							}else if(data.status==10){
								alert("需要重新登录");
							}
							        }).catch(function (error) {
							          console.log(error)
							        });
							
						}else{
							
							let dom = document.getElementsByClassName("z");
							if(dom[1].value===dom[2].value){
								let data = {};
								data.username = dom[0].value;
								data.password = dom[1].value;
								data.age = dom[3].value;
								data.sex = dom[4].value;
								console.log(data)
								axios.post("http://"+_this.tarip+"/portal/client/register",data).then(function(res){
									console.log(res);
									if(res.data.status==0){
										alert("注册成功");
										self.location.href="http://"+_this.ip+"/login-manager/login.html";
									}else{
										alert("注册失败");
									}
								})
							}else{
								alert("两次输入密码不一致")
							}
							
						}
						
					}
				}
			})