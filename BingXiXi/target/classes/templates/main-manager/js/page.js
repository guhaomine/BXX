function Page(iAbsolute,sTableId,sTBodyId,pageBar){
    this.absolute = iAbsolute; //每页最大记录数
    this.tableId = sTableId;
    this.tBodyId = sTBodyId;
    this.pageBar = pageBar;

//this.tFootId = sTFootId;
    this.rowCount = 0;//记录数
    this.pageCount = 0;//页数
    this.pageIndex = 0;//页索引
    this.__oTable__ = null;//表格引用
    this.__oTBody__ = null;//要分页内容
    this.__oTFoot__ = null;//要分页内容
    this.__dataRows__ = 0;//记录行引用
    this.__oldTBody__ = null;
    this.__init__(); //初始化;
};
/**
 * 初始化
 */
Page.prototype.__init__ = function(){
    this.__oTable__ = document.getElementById(this.tableId);//获取table引用
    this.__oTBody__ = this.__oTable__.tBodies[this.tBodyId];//获取tBody引用
//this.__oTFoot__= document.getElementById(this.tFootId)
    this.__dataRows__ = this.__oTBody__.rows;
    this.rowCount = this.__dataRows__.length;
    try {
        this.absolute = (this.absolute <= 0) || (this.absolute > this.rowCount) ? this.rowCount : this.absolute;
        this.pageCount = parseInt(this.rowCount % this.absolute == 0 ? this.rowCount / this.absolute : this.rowCount / this.absolute + 1);
    }catch(exception){
    }
    this.__updateTableRows__();
};

Page.prototype.getBar = function(obj){
    var bar = document.getElementById(obj.id);
    bar.innerHTML = "每页" + this.absolute + "条/共" + this.rowCount + "条";// 第2页/共6页 首页 上一页 1 2 3 4 5 6 下一页 末页
}
/**
 * 下一页
 */
Page.prototype.nextPage = function() {
    if(this.pageIndex + 1 < this.pageCount) {
        this.pageIndex += 1;
        this.__updateTableRows__();
    }
//FY();
};
/**
 * 上一页
 */
Page.prototype.prePage = function() {
    if(this.pageIndex >= 1) {
        this.pageIndex -= 1;
        this.__updateTableRows__();
    }
};
/**
 * 首页
 */
Page.prototype.firstPage = function() {
    if(this.pageIndex != 0) {
        this.pageIndex = 0;
        this.__updateTableRows__();
    }
};
/**
 * 尾页
 */
Page.prototype.lastPage = function() {
    if(this.pageIndex + 1 != this.pageCount) {
        this.pageIndex = this.pageCount - 1;
        this.__updateTableRows__();
    }
};
/**
 * 页定位方法
 */
Page.prototype.aimPage = function(iPageIndex) {
    if(iPageIndex > this.pageCount - 1) {
        this.pageIndex = this.pageCount - 1;
    }else if(iPageIndex < 0) {
        this.pageIndex = 0;
    }else {
        this.pageIndex = iPageIndex;
    }
    this.__updateTableRows__();
};

/**
 * 执行分页时，更新显示表格内容
 */
Page.prototype.__updateTableRows__ = function() {
    var iCurrentRowCount = this.absolute * this.pageIndex;
    var iMoreRow = this.absolute + iCurrentRowCount > this.rowCount ? this.absolute + iCurrentRowCount - this.rowCount : 0;
    var tempRows = this.__cloneRows__();
    var removedTBody = this.__oTable__.removeChild(this.__oTBody__);
    var newTBody = document.createElement("tbody");
    newTBody.setAttribute("id", this.tBodyId);

    for(var i = iCurrentRowCount;i < this.absolute + iCurrentRowCount - iMoreRow;i++) {
        newTBody.appendChild(tempRows[i]);
    }
    this.__oTable__.appendChild(newTBody);

//页脚显示分页
    var pageBar = document.getElementById(this.pageBar);//分页工具栏
    pageBar.innerHTML = "";

    var leftBar = document.createElement("div"),cur;
    leftBar.className = 'leftBar col-lg-8';
    cur = this.pageIndex * this.absolute + ' 到 ' + (this.pageIndex + 1) * this.pageCount;
    if(this.pageIndex === 0){
        cur = ' 1 到 ' + this.absolute;
    }
    if(this.pageIndex === this.pageCount - 1){
        cur = this.pageIndex * this.absolute + ' 到 ' + this.rowCount;
    }
    leftBar.innerHTML = "当前显示 " + cur + " 条，共 " + this.rowCount + " 条记录";
    var rightBar = document.createElement('div');
    rightBar.className = 'rightBar col-lg-4';
    var btnPre = document.createElement("a");
    var btnNext = document.createElement("a");

    btnPre.className = 'button-left';
    btnPre.href = "javascript:this.prePage()";
    btnPre.innerHTML = '<button class="prev btn btn-default">上一页</button>';

    rightBar.appendChild(btnPre);
    btnNext.className = 'button-right';
    
    btnNext.href = "javascript:this.page.nextPage()";
	// btnNext.onclick =function(){nextPage()};
    btnNext.innerHTML = '<button class="prev btn btn-default">下一页</button>';
    rightBar.appendChild(btnNext);

    // pageBar.appendChild(leftBar);
    // pageBar.appendChild(rightBar);

    if(this.pageIndex == 0){
        btnPre.disabled = "disabled";
        btnPre.style.cursor = 'default';
    }
    if(this.pageCount - 1 == this.pageIndex){
        btnNext.disabled = "disabled";
        btnNext.style.cursor = 'default';
    }
////页脚显示分页结束

    /**
     this.dataRows为this.oTBody的一个引用，
     移除this.oTBody那么this.dataRows引用将销失,
     code:this.dataRows = tempRows;恢复原始操作行集合.
     */
    this.__dataRows__ = tempRows;
    this.__oTBody__ = newTBody;
};
/**
 * 克隆原始操作行集合
 */
Page.prototype.__cloneRows__ = function() {
    var tempRows = [];
    for(var i=0;i < this.__dataRows__.length;i++) {
        /**
         code:this.dataRows[i].cloneNode(param),
         param = 1 or true:复制以指定节点发展出去的所有节点,
         param = 0 or false:只有指定的节点和它的属性被复制.
         */
        tempRows[i] = this.__dataRows__[i].cloneNode(1);
    }
    return tempRows;
};