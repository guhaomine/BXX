axios.defaults.withCredentials=true;
var menu = new Vue({
    el:".main",
    data:{
        ip:"192.168.0.105:8080",
        localip:"192.168.0.105:8080",
        userType:1,
        product:[],
        userlist:[],
        allproduct:[],
        pro1:[],
        usermessage:{
            username:"admin",age:22,sex:"男",tel:"12322312",address:"西南石油大学"
        },
        listproduct:[],
        shangjia:[
            "img/taobao.jpg",
            "img/meituan.jpg",
            "img/jingdong.jpg",
            "img/elme.jpg"
        ],
        pagepar:{
            pages:1,
            page:1,
            size:5,
            currentpage:0,
            isnextpage:false,
            isprepage:false,
            isnextpage1:false,
            isprepage1:false,
            isnextpage2:false,
            isprepage2:false,
            isnextpage3:false,
            isprepage3:false
        },
        user:{}
    },
    created() {
        var url = window.location.href;
        if(/\?/.test(url)){
            var result = url.split("?")[1];
            var reg = /[0-9]$/;
            var value = result.split("=")[1].match(reg)[0];
            this.userType = parseInt(value);
        }

    },
    mounted() {

        let _this =this;
        axios.get("http://"+_this.ip+"/portal/client/get_information").then(function(res){
            if(res.data.status==1){
                alert("请先登录")
                self.location.href="http://"+_this.localip+"/login-manager/login.html";
            }else if(res.data.status==0){
                _this.usermessage = res.data.data;
                _this.pageparinit(9);
            }
        })
        if(this.userType){
            let dom = document.getElementsByClassName("menu");
            dom[5].remove();
            dom[4].remove();

        }
        showtitle.style.display = "block";
        showmain.style.display = "block";
        mainpindan.style.display= "block";

    },

    methods:{
        pageparinit:function(e){
            let _this =this;
            _this.pagepar.page = 1;

            if(e==2){
                axios.get("http://"+_this.ip+"/backend/bill/listMyActiveBill",{
                    params:{page:1,size:_this.pagepar.size}}).then(function(res){
                    _this.pagepar.isnextpage1 = res.data.data.hasNextPage;
                    _this.pagepar.isprepage1=res.data.data.hasPreviousPage;
                    _this.pagepar.pages=res.data.data.pages;

                    _this.product = res.data.data.list;
                    _this.product.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==3){
                axios.get("http://"+_this.ip+"/backend/bill/listMyBill",{
                    params:{page:1,size:_this.pagepar.size}}).then(function(res){
                    _this.pagepar.isnextpage = res.data.data.hasNextPage;
                    _this.pagepar.isprepage=res.data.data.hasPreviousPage;
                    _this.pagepar.pages=res.data.data.pages;
                    _this.pro1 = res.data.data.list;
                    _this.pro1.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==4){
                axios.get("http://"+_this.ip+"/backend/client/listAllClient",{
                    params:{page:1,size:_this.pagepar.size}
                }).then(function(res){
                    _this.pagepar.isnextpage3 = res.data.data.hasNextPage;
                    _this.pagepar.isprepage3=res.data.data.hasPreviousPage;
                    _this.pagepar.pages=res.data.data.pages;
                    _this.userlist = res.data.data.list;})
            }else if(e==5){
                let m =[];
                axios.get("http://"+_this.ip+"/portal/bill/getActiveList",{
                    params:{page:1,rows:_this.pagepar.size}
                }).then(function(res){
                    _this.pagepar.isnextpage2 = res.data.data.hasNextPage;
                    _this.pagepar.isprepage2=res.data.data.hasPreviousPage;
                    _this.pagepar.pages=res.data.data.pages;
                    _this.allproduct = res.data.data.list;
                    _this.allproduct.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })
                })
            }else if(e==9){
                axios.get("http://"+_this.ip+"/portal/bill/getActiveList").then(function(res){
                    if(res.status==200)
                        _this.listproduct = res.data.data.list;
                    _this.readmessage(_this.listproduct);})

            }},
        getpercount:function(id){
            var s = 3;
            console.log((axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+id)))
            axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+id).then(function(res){ return s=4 });

            return s;
        },
        nextpage:function(e){
            let _this = this;
            if(e==1&&_this.pagepar.isnextpage){		//历史
                _this.pagepar.page++;
                axios.get("http://"+_this.ip+"/backend/bill/listMyBill",{
                    params:{page:_this.pagepar.page,size:_this.pagepar.size}
                }).then(function(res){_this.pro1 = res.data.data.list;_this.pagepar.isnextpage=res.data.data.hasNextPage;_this.pagepar.isprepage=res.data.data.hasPreviousPage;
                    _this.pro1.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==2&&_this.pagepar.isnextpage1){//正在
                _this.pagepar.page++;
                axios.get("http://"+_this.ip+"/backend/bill/listMyActiveBill",{
                    params:{page:_this.pagepar.page,size:_this.pagepar.size}
                }).then(function(res){_this.product = res.data.data.list;_this.pagepar.isnextpage1=res.data.data.hasNextPage;_this.pagepar.isprepage1=res.data.data.hasPreviousPage;
                    _this.product.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==3&&_this.pagepar.isnextpage2){//所有
                _this.pagepar.page++;
                axios.get("http://"+_this.ip+"/portal/bill/getActiveList",{
                    params:{page:_this.pagepar.page,rows:_this.pagepar.size}
                }).then(function(res){_this.allproduct = res.data.data.list;_this.pagepar.isnextpage2=res.data.data.hasNextPage;_this.pagepar.isprepage2=res.data.data.hasPreviousPage;
                    _this.allproduct.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==4&&_this.pagepar.isnextpage3){//用户
                _this.pagepar.page++;
                axios.get("http://"+_this.ip+"/backend/client/listAllClient",{
                    params:{page:_this.pagepar.page,rows:_this.pagepar.size}
                }).then(function(res){_this.userlist = res.data.data.list;_this.pagepar.isnextpage3=res.data.data.hasNextPage;_this.pagepar.isprepage3=res.data.data.hasPreviousPage;})
            }


        },
        prepage:function(e){
            let _this = this;
            if(e==1&&_this.pagepar.isprepage){		//历史
                _this.pagepar.page--;
                axios.get("http://"+_this.ip+"/backend/bill/listMyBill",{
                    params:{page:_this.pagepar.page,size:_this.pagepar.size}
                }).then(function(res){_this.pro1 = res.data.data.list;_this.pagepar.isprepage=res.data.data.hasPreviousPage;_this.pagepar.isnextpage=res.data.data.hasNextPage;
                    _this.pro1.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==2&&_this.pagepar.isprepage1){//正在
                _this.pagepar.page--;
                axios.get("http://"+_this.ip+"/backend/bill/listMyActiveBill",{
                    params:{page:_this.pagepar.page,size:_this.pagepar.size}
                }).then(function(res){_this.product = res.data.data.list;_this.pagepar.isprepage1=res.data.data.hasPreviousPage;_this.pagepar.isnextpage1=res.data.data.hasNextPage;
                    _this.product.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==3&&_this.pagepar.isprepage2){//所有
                _this.pagepar.page--;
                axios.get("http://"+_this.ip+"/portal/bill/getActiveList",{
                    params:{page:_this.pagepar.page,rows:_this.pagepar.size}
                }).then(function(res){_this.allproduct = res.data.data.list;_this.pagepar.isPrepage2=res.data.data.hasPreviousPage;_this.pagepar.isnextpage2=res.data.data.hasNextPage;
                    _this.allproduct.forEach(function(s){
                        axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+s.id).then(function(re){s.contactImg=re.data.data.length});
                    })})
            }else if(e==4&&_this.pagepar.isprepage3){//用户
                _this.pagepar.page--;
                axios.get("http://"+_this.ip+"/backend/client/listAllClient",{
                    params:{page:_this.pagepar.page,rows:_this.pagepar.size}
                }).then(function(res){_this.userlist = res.data.data.list;_this.pagepar.isnextpage3=res.data.data.hasNextPage;_this.pagepar.isprepage3=res.data.data.hasPreviousPage;})
            }
        },
        showMessage:function(e){
            let dom = document.getElementsByClassName("mm");
            dom[0].innerText = "商品名:"+e.product;
            dom[1].innerText = "商家:"+e.shop;
            dom[2].innerText = "取货地址:"+e.address;
            dom[3].innerText = "目标:"+e.target;
            dom[4].innerText = "开始时间:"+e.starttime;
            dom[5].innerText = "截止时间:"+e.endtime;
            dom[6].innerText = "更新日期:"+e.updatetime;
            // dom[7].innerText = "联系方式:"+12345678;
            menu_item2.style.display = "block";
        },
        showMessage1:function(e){
            let data = e.data;
            let dom = document.getElementsByClassName("mm");
            dom[0].innerText = "参与人数:"+data.length;
            let s='';
            for(let i=0;i<data.length;i++){
                s+=data[i].username+",";
                if((i+1)%5==0){
                    s+="</br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                }
            }
            dom[1].innerHTML = "参与用户:"+s;
            dom[2].innerText = '';
            dom[3].innerText = '';
            dom[4].innerText = '';
            dom[5].innerText = '';
            dom[6].innerText = '';
            dom[7].innerText ='';
            menu_item2.style.display = "block";
        },
        getDate:function(){
            let da = new Date();
            let Y = da.getFullYear();
            let M = da.getMonth()+1;M = ("00" + M).substr(-2,2);
            let D = da.getDay()+1;D = ("00" + D).substr(-2,2);
            let h = da.getHours();
            let m =  ("00" + da.getMinutes()).substr(-2,2);
            let s = ("00" + da.getSeconds()).substr(-2,2);
            return ""+Y+"-"+M+"-"+D+" "+h+":"+m+":"+s
        },
        btnChange:function(e){
            let _this = this;
            if(e==2||e==3||5){
                _this.pageparinit(e);
            }
            menu_item2.style.display = "none";
            let menu_items = document.getElementsByClassName("menu-item");
            let doms = document.getElementsByClassName("menu");
            for(let i = 0;i<doms.length;i++){
                menu_items[i].style.display= e==i?"block":"none";
                doms[i].style.cssText = e==i?"background:#d9534f;color:#fff":"background:#EEEEEE,color:black";
            }

        },
        init:function(){
            let dom = document.getElementsByClassName("menu-item");
            for(let i = 1;i<dom.length;i++){
                dom[i].style.display = "none";
            }
            messageshow.style.display = "block";
            let dom1 = document.getElementsByClassName("menu");
            for(let i = 1;i<dom1.length;i++){
                dom1[i].style.cssText="background:#EEEEEE,color:black";;
            }
            dom1[0].style.cssText="background:#d9534f;color:#fff";

        },
        getuserpage:function(src,name,sj,ad,mu,id){
            let col4 = document.createElement('div');
            col4.setAttribute('class','col-lg-4');
            let col6_1 = document.createElement('div');
            col6_1.setAttribute('class','col-lg-6');
            let img = document.createElement('img');
            img.style.cssText = "float: right;width: 80px;";
            img.setAttribute('src',src);
            col6_1.appendChild(img);
            col4.appendChild(col6_1);
            let col6_2 = document.createElement('div');
            col6_2.setAttribute('class','col-lg-6');
            let row1 = document.createElement('div');
            row1.setAttribute('class','row');
            row1.style.cssText = "font-weight: 900;font-size: 20px;";
            row1.innerText = name;
            let row2 = document.createElement('div');
            row2.setAttribute('class','row');
            row2.innerText = sj;
            let row3 = document.createElement('div');
            row3.setAttribute('class','row');
            row3.innerText = ad;
            let row4 = document.createElement('div');
            row4.setAttribute('class','row');
            row4.style.cssText = "color: red;";
            row4.innerText = mu;
            col6_2.appendChild(row1);
            col6_2.appendChild(row2);
            col6_2.appendChild(row3);
            col6_2.appendChild(row4);
            col4.appendChild(col6_2);
            col4.addEventListener("click",this.easy(id));
            fa.appendChild(col4);
        },
        easy:function(data){
            let product = this.listproduct;
            return function(){
                showtitle.style.display = "none";
                let da = product.filter(function(s){
                    if(s.id == data){
                        return s;
                    }
                });
                let dom = document.getElementsByClassName('msg');
                dom[0].innerText=da[0].product;
                dom[0].setAttribute("pid",data);
                dom[1].innerText="商家:"+da[0].shop;
                dom[2].innerText="目标:"+da[0].target;
                dom[3].innerText="取货地址:"+da[0].address;
                dom[4].innerText="截止时间:"+da[0].endtime;
                pagemessage.style.display = 'block';
                mainpindan.style.display = "none";
                fabupindan.style.display = "none";
            }

        },
        readmessage:function(data){

            fa.innerHTML =null;
            fa.innerText =null;
            let s = this.shangjia;
            for(let i = 0;i<data.length;i++){
                let id = data[i].productImg;
                this.getuserpage(s[id],data[i].product,data[i].shop,data[i].address,data[i].target,data[i].id);
            }
        },
        getall:function(){
            this.readmessage(this.listproduct);
        },
        saixuan:function(e){
            let data = this.listproduct;
            let type = parseInt(e);
            var newlist = data.filter(function(s){
                if(s.productImg == type){
                    return s;
                }
            })
            // console.log(newlist)
            this.readmessage(newlist);
        },
        search:function(){
            let target = pdname.value;
            let reg = new RegExp(target);
            let data = this.listproduct;
            var newlist = data.filter(function(s){
                if(reg.test(s.product)){
                    return s;
                }
            })
            if(newlist.length>0){
                this.readmessage(newlist);
            }else{
                alert("没有该拼单");
            }

        },
        fabupindanmodel:function(e,id){
            let _this = this;
            if(e=='GO'){
                showtitle.style.display = "none";
                showforuser.style.display = "none";
                showmain.style.display = "block";
                mainpindan.style.display = "none";
                fabupindan.style.display = "block";
                pagemessage.style.display = 'none';
            }else if(e=='FANHUI'){
                _this.init();
                _this.pageparinit(9);
                showtitle.style.display = "block";
                showforuser.style.display = "none";
                showmain.style.display = "block";
                mainpindan.style.display = "block";
                fabupindan.style.display = "none";
                pagemessage.style.display = 'none';
            }else if(e=='MINE'){
                _this.init();
                showtitle.style.display = "none";
                showmain.style.display = "none";
                showforuser.style.display = "block";
                menubur.style.display = "block";
                document.getElementsByClassName("menu")[0].style.cssText="display:block; background:#d9534f;color:#fff";
            }else if(e=='OUT'){
                axios.get("http://"+_this.ip+"/portal/client/logout").then(function(res){
                    self.location.href = "http://"+_this.localip+"/login-manager/login.html"
                })
            }else if(e=='SAVE'&&confirm("确认修改!")){
                let client = _this.usermessage
                axios.put("http://"+_this.ip+"/portal/client/update_information",client).then(function(res){
                    if(res.status==200){alert("修改成功")}
                })
            }else if(e=='PASS'&&confirm("确认修改!")){
                let dom = document.getElementsByClassName('pas');
                if(dom[1].value!=dom[2].value){
                    alert("两次输入密码不一致，修改密码失败")
                }else{
                    let o = dom[0].value;
                    let n = dom[1].value;
                    axios.patch("http://"+_this.ip+"/portal/client/resetPassword?oldPassword="+o+"&newPassword="+n).then(function(res){
                        alert(res.data.data);
                    })
                }

            }else if(e=='CREAT'){
                let dom = document.getElementsByClassName('creat');
                let data = {};
                data.product = dom[0].value;
                data.leaderid = dom[1].getAttribute("userid");
                let temp = dom[2].value;
                data.shop = temp==0?"淘宝":temp==1?"美团":temp==2?"京东":"饿了么";
                data.productImg = temp;
                data.address = dom[3].value;
                data.target = dom[4].value;
                let datetime = dom[5].value;
                data.endtime = datetime.split('T')[0]+" "+datetime.split('T')[1];

                axios.post("http://"+_this.ip+"/backend/bill/createBill",data).then(function(res){

                    if(res.status==200)
                        if(res.data.status ==0){
                            alert("发布成功");
                            _this.fabupindanmodel('FANHUI')
                        }else{alert("发布失败")}
                })

            }else if(e=='END'&&confirm("确认终止!")){
                let data = id;
                if(data!=null){
                    axios.patch("http://"+_this.ip+"/backend/bill/shutdownBill?id="+data).then(function(res){

                        if(res.status==200)
                            if(res.data.status ==0){
                                _this.pageparinit(5);
                            }else{alert(res.data.msg)}
                    })
                }
            }else if(e=='JOIN'&&confirm("确认加入!")){
                let id = document.getElementsByClassName('msg')[0].getAttribute("pid")
                axios.patch("http://"+_this.ip+"/backend/bill/joinBill?id="+id).then(function(res){
                    if(res.status==200)
                        if(res.data.status ==0){
                            _this.fabupindanmodel('FANHUI')
                        }else{alert(res.data.msg)}
                })

            }else if(e=='QUIT'&&confirm("确认退出!")){
                let id = document.getElementsByClassName('msg')[0].getAttribute("pid")
                axios.patch("http://"+_this.ip+"/backend/bill/quitBill?id="+id).then(function(res){
                    if(res.status==200)
                        if(res.data.status ==0){
                            _this.fabupindanmodel('FANHUI')
                        }else{alert(res.data.msg)}
                })
            }else if(e=='DELUSER'&&confirm("确认删除!")){
                axios.delete("http://"+_this.ip+"/backend/client/deleteClient?id="+id).then(function(res){
                    if(res.status==200)
                        if(res.data.status==0){
                        }else{alert(res.data.msg)}
                })
            }else if(e=='USER'){
                axios.get("http://"+_this.ip+"/backend/bill/listBillClient?id="+id).then(function(res){
                    if(res.status==200)
                        if(res.data.status==0){
                            let item = res.data
                            _this.showMessage1(item);
                        }else{alert(res.data.msg)}
                })
            }
        }

    }
})
	