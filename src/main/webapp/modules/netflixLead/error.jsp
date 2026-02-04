<%@include file="/admin-init.jsp"%>

<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="popup-wrap" class="modal fade lead-error-modal vip-modal" tabindex="-1" role="dialog" data-hash="popup-wrap">
	<div class="modal-dialog">
		<div class="modal-content" style="margin: 0 auto;">
			<button type="button" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
			<div class="modal-body with-shape">
				<div class="row">
					<div class="col-xs-12 margin-top-50 text-center">
						<p><liferay-ui:message key="lead-form-error"/></p>
					</div>
				</div>	
			</div>
			<div class="modal-footer">
				<button id="prompt-ok" data-dismiss="modal" type="button"
					 class="btn btn-primary">
					<liferay-ui:message key="leadform.button.close.name" />
				</button>
			</div>
		</div>
	</div>
</div>

<div id="popup-wrap" class="modal fade lead-success-modal vip-modal"  role="dialog"  data-hash="popup-wrap">
	<div class="modal-dialog">
		<div class="modal-content" style="margin: 0 auto;">
			<button type="button" class="close" data-dismiss="modal"><i class="fa fa-times" aria-hidden="true"></i></button>
			<div class="modal-body with-shape">
				<div class="row">
					<div class="col-xs-12 margin-top-50 text-center">
						<p><liferay-ui:message key="lead-form-success"/></p>
					</div>
				</div>	
			</div>
			<div class="modal-footer">
				<button id="prompt-ok" data-dismiss="modal" type="button"
					 class="btn btn-primary" data-target="#popup-wrap">
					<liferay-ui:message key="leadform.button.close.name" />
				</button>
			</div>
		</div>
	</div>
</div>

<c:if test="${error!=null}">
	<script type="text/javascript">
		jQuery(".lead-error-modal").modal();
	</script>
</c:if>
<c:if test="${status!=null}">
	<script type="text/javascript">
		jQuery(".lead-success-modal").modal();
	</script>
</c:if>

<script type="text/javascript">
(function(){
	var btnClicked = false;
	var clicked = false;
	jQuery('#popup-wrap').click(function(event){
		if(!btnClicked && !clicked) {
			jQuery("#popup-wrap").modal("hide");
			clicked = true;
		}
		event.preventDefault();
	});
	jQuery('#error-popup-btn').click(function(event){
		jQuery("#popup-wrap").modal("hide");
		event.preventDefault();
		btnClicked = true;
	});
	jQuery(document).ready(function() {
		jQuery("#popup-wrap").modal("hide");
		$('#portlet_Netflixleadform_WAR_LeadSalesportlet').css({'visibility':'visible','display':'block'});
		//jQuery("#popup-wrap").modal();
	});
	$('#popup-wrap').on('hidden.bs.modal', function () {
	    $('#portlet_Netflixleadform_WAR_LeadSalesportlet').css({'visibility':'hidden','display':'none'});
	});
})();
</script>


