<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="../includes/header.jsp"%>


<div class="row">
  <div class="col-lg-12">
    <h1 class="page-header">게시글 수정</h1>
  </div>
  <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
  <div class="col-lg-12">
    <div class="panel panel-default">

      <div class="panel-heading">Board Modify</div>
      <!-- /.panel-heading -->
      <div class="panel-body">

	      <form role="form" action="/board/modify" method="post">
	        <%-- 
	        <input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum }"/>'>
	        <input type='hidden' name='amount' value='<c:out value="${cri.amount }"/>'>
		    <input type='hidden' name='type' value='<c:out value="${cri.type }"/>'>
			<input type='hidden' name='keyword' value='<c:out value="${cri.keyword }"/>'> --%>
	      
	 
	        <input type='hidden' name='bno' value='<c:out value="${board.bno }"/>'>
	        <%-- 
			<div class="form-group">
			  <label>번호</label> 
			  <input class="form-control" name='bno' 
			     value='<c:out value="${board.bno }"/>' readonly="readonly">
			</div> --%>
			
			<div class="form-group">
			  <label>제목</label> 
			  <input class="form-control" name='title' 
			    value='<c:out value="${board.title }"/>' >
			</div>
			
			<div class="form-group">
			  <label>내용</label>
			  <textarea class="form-control" rows="3" name='content' ><c:out value="${board.content}"/></textarea>
			</div>
			
			<div class="form-group">
			  <label>Writer</label> 
			  <input class="form-control" name='writer'
			    value='<c:out value="${board.writer}"/>' readonly="readonly">            
			</div>
			
			<div class="form-group">
			  <label>작성일</label> 
			  <input class="form-control" name='regDate'
			    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.regdate}" />'  readonly="readonly">            
			</div>
			
			<div class="form-group">
			  <label>수정일</label> 
			  <input class="form-control" name='updateDate'
			    value='<fmt:formatDate pattern = "yyyy/MM/dd" value = "${board.updateDate}" />'  readonly="readonly">            
			</div>
			
	    	<button type="submit" data-oper='modify' class="btn btn-default">수정</button>
			<button type="submit" data-oper='remove' class="btn btn-danger">삭제</button>
			<button type="submit" data-oper='list' class="btn btn-info">목록으로</button>
	      </form>

      </div>
      <!--  end panel-body -->

    </div>
    <!--  end panel-body -->
  </div>
  <!-- end panel -->
</div>
<!-- /.row -->

<script type="text/javascript">
$(document).ready(function() {

  var formObj = $("form");

  $('button').on("click", function(e){
    
	// <form> 태그가 모든 버튼을 submit으로 처리하지 않고 무시하도록 처리
    e.preventDefault(); 	 
    
 	// <button> 태그의 'data-oper' 속성 이용
    var operation = $(this).data("oper");
    console.log(operation);
    
    if(operation === 'remove'){
      formObj.attr("action", "/board/remove");	// 기존 form 액션 url 변경
      
    }else if(operation === 'list'){
      // list로 이동
      formObj.attr("action", "/board/list").attr("method","get");	// 기존 form 액션 url, method 변경
      formObj.empty();
      /* 
      self.location = "/board/list";
      return; */
      
      /* 
      
      var pageNumTag = $("input[name='pageNum']").clone();
      var amountTag = $("input[name='amount']").clone();
      var keywordTag = $("input[name='keyword']").clone();
      var typeTag = $("input[name='type']").clone();      
      
      formObj.append(pageNumTag);
      formObj.append(amountTag);
      formObj.append(keywordTag);
      formObj.append(typeTag);	   */     
    }
    
    formObj.submit();	// 
  });

});
</script>
  




<%@include file="../includes/footer.jsp"%>
