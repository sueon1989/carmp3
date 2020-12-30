<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



<%@include file="../includes/header.jsp" %>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">게시판</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	
	<!-- /.row -->
	<div class="row">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					게시판 목록 페이지
					<button id='regBtn' type="button" class="btn btn-xs pull-right">글쓰기</button>
				</div>
	            
				<!-- /.panel-heading -->
				<div class="panel-body">
					<table width="100%" class="table table-striped table-bordered table-hover" id="dataTables-example">
						<thead>
							<tr>
								<th>#번호</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>수정일</th>
							</tr>
	                    </thead>

						<!-- 게시글 목록 불러오기 -->
						<c:forEach items="${list}" var="board">
							<tr>
								<td><c:out value="${board.bno}" /></td>
								
								<%-- <td><a href='/board/get?bno=<c:out value="${board.bno}"/>'>
									<c:out value="${board.title}"/></a></td> --%>
								<td><a class='move' href='<c:out value="${board.bno}"/>'>
									<c:out value="${board.title}" /></a></td>
									
								<td><c:out value="${board.writer}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regdate}" /></td>
								<td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.updateDate}" /></td>
								
							</tr>
							<tr>
	
							</tr>
						</c:forEach>
						
					</table>
					<!-- /.table-responsive -->
					
					<!-- p308 추가 -->
					<!-- 페이지 번호 표시 -->
					<div class='pull-right'>
						<ul class="pagination">
						
							<c:if test="${pageMaker.prev}">	<!-- true이면 Previous 표시 -->
								<li class="paginate_button previous"><a href="/board/list?pageNum=${pageMaker.startPage -1 }&amount=${pageMaker.cri.amount }">Previous</a></li>
								<%-- <li class="paginate_button previous"><a href="${pageMaker.startPage -1 }">Previous</a></li> --%>
							</c:if>
			
							<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage }">
								<%-- <li class="paginate_button"><a href="#">${num}</a></li> --%>
								<li class="paginate_button  ${pageMaker.cri.pageNum == num ? " active":""} ">
									<a href="/board/list?pageNum=${num}&amount=${pageMaker.cri.amount }">${num}</a>
									<%-- <a href="${num}">${num}</a> --%>
								</li>
							</c:forEach>
			
							<c:if test="${pageMaker.next}">
								<li class="paginate_button next"><a href="/board/list?pageNum=${pageMaker.endPage +1 }&amount=${pageMaker.cri.amount }">Next</a></li>
								<%-- <li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a></li> --%>
							</c:if>
						</ul>
					</div>
					<!-- end Pagination -->
					<!-- p308 추가 끝 -->
					
					<!-- 311p 추가 -->
					<!-- <a> 태그가 원래의 동작을 못하도록 JavaScript 처리... 
					c:out을 사용하는 것이 더 좋은 방법이지만, 간단히 사용하기 위해 EL태그로 처리 -->
					<form id='actionForm' action="/board/list" method='get'>
						<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
						<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
		
						<%-- 
						<input type='hidden' name='type' value='<c:out value="${ pageMaker.cri.type }"/>'> 
						<input type='hidden' name='keyword' value='<c:out value="${ pageMaker.cri.keyword }"/>'> --%>
					</form>
					<!-- 311p 추가 끝 -->
					
	                
	                <!-- p248 추가 -->
					<!-- 모달창 (Modal) -->
					<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
						aria-labelledby="myModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title" id="myModalLabel">처리 결과</h4>
								</div>
								<div class="modal-body">처리가 완료되었습니다.</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<!-- <button type="button" class="btn btn-primary">Save changes</button> -->
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
	                <!-- p248 추가 끝 -->
	                
	                
				</div>
				<!-- /.panel-body -->
	            
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	
	
	
<script type="text/javascript">
	$(document).ready(function() {

		var result = '<c:out value="${result}"/>';	// 보안을 위해
		// var result = '${param.result}';			// get 타입으로 보내온 result 값을 읽고
		// var result = '${result}';				// result 값을 읽고
		console.log("글번호: "+ result);			// 크롬F12 console 클릭)
		
		var status = '<c:out value="${status}"/>';
				
		/* p248 추가 */
		/* 모달창 */
		checkModal(result);	
		
		/* p257 추가 */
		/* 뒤로가기 시 모달 창이 한번 더 뜨는 문제 해결하기 */
		console.log("전" + history.state);
		history.replaceState({}, null, null);
		console.log("후" + history.state);
		
		function checkModal(result) {

			// 교수님> 글을 작성하거나 history가 없다면 모달창 띄우기
			/* if (result != '' && history.state){
				// alert("글 등록 완료: "+ result);	// 알림창
				$(".modal-body").html("게시글 " + parseInt(result) + " 번이 등록되었습니다.");
				$("#myModal").modal("show");
			} */
						
			// 과제내용
			if (!(history.state)){
				if(status == 'register_success'){
					//alert(result+"글등록 완료");
					$(".modal-body").html("게시글 " + result + "번이 등록되었습니다.");
					$("#myModal").modal("show");
					
				}else if(status == 'remove_success'){
					$(".modal-body").html("게시글 " + result + "번이 삭제되었습니다.");
					$("#myModal").modal("show");
					
				}else if(status == 'modify_success'){
					$(".modal-body").html("게시글 " + result + "번이 수정되었습니다.");
					$("#myModal").modal("show");
					
				}
			}
			
			// 교재>
			// 리스트만 요청하거나(result가 빈 값('')이거나 
			// 뒤로가기로 접근하는 경우(history가 있거나)
			// 모달창을 띄우지 않는다
			/* 
			if (result === '' || history.state) {	// === 엄격한 비교 (타입까지), == 일반 비교 (값)	
				return;
			}
			
			
			if (parseInt(result) > 0) {
				$(".modal-body").html("게시글 " + result + " 번이 등록되었습니다.");
			}
			$("#myModal").modal("show");
			 */
			
			// Register New Board 버튼 클릭하면
			$("#regBtn").on("click", function() {
				self.location = "/board/register";	// 게시물 등록 페이지로 이동
			});
		}
		
		
		/* p312 추가 */
		/* 페이지 번호 */
/* 		var actionForm = $("#actionForm");
		$(".paginate_button a").on("click", function(e) {

			e.preventDefault();		// <a> 태그를 클릭해도 페이지 이동이 없도록
			console.log('click');	// 
	
			actionForm.find("input[name='pageNum']").val($(this).attr("href"));	// pageNum 값은 href 속성값으로 변경
			actionForm.submit();
		}); */
		
		/* 
		$(".move")
				.on(
						"click",
						function(e) {

							e.preventDefault();
							actionForm
									.append("<input type='hidden' name='bno' value='"
											+ $(this).attr(
													"href")
											+ "'>");
							actionForm.attr("action",
									"/board/get");
							actionForm.submit();

						});

		var searchForm = $("#searchForm");

		$("#searchForm button").on(
				"click",
				function(e) {

					if (!searchForm.find("option:selected")
							.val()) {
						alert("검색종류를 선택하세요");
						return false;
					}

					if (!searchForm.find(
							"input[name='keyword']").val()) {
						alert("키워드를 입력하세요");
						return false;
					}

					searchForm.find("input[name='pageNum']")
							.val("1");
					e.preventDefault();

					searchForm.submit();

				});
		*/
	}); 
</script>




<%@include file="../includes/footer.jsp" %>