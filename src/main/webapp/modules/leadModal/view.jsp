<%@include file="/init-front.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="javax.portlet.PortletPreferences"%>

<%
	ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	String currentUrl = td.getURLCurrent();
	String lang = themeDisplay.getLocale().toString();


	PortletPreferences prefs = renderRequest.getPreferences();
	String userType = prefs.getValue("userType", "private");
	
	String installationAddress = prefs.getValue("installationAddress", "false");
	String showSurvey = prefs.getValue("showSurvey", "false");
	String agreePersonalData = prefs.getValue("agreePersonalData", "false");
	
	String formDescMK = prefs.getValue("formDescMK", "");
	String formDescAL = prefs.getValue("formDescAL", "");
	String formDescEN = prefs.getValue("formDescEN", "");
%>

<jsp:include page="/modules/leadModal/error.jsp" />

<div class="modal fade vip-modal" id="popup-wrap-modal" data-hash="popup-wrap-modal">
	<div class="modal-dialog">
		<div class="modal-content" style="margin: 0 auto;">
			<button type="button" class="close" data-dismiss="modal">
				<i class="fa fa-times" aria-hidden="true"></i>
			</button>
			<div class="modal-body">
				<div class="row">
					<div class="col-12 mb-4">
						<h5 class="font-secondary-bold">
							<%if (lang!=null && lang.equals("mk_MK") && !formDescMK.equals("")){%>
								<%= formDescMK%>
							<%}else if(lang!=null && lang.equals("en_US") && !formDescEN.equals("")){ %>
								<%= formDescEN%>
							<%}else if(lang!=null && lang.equals("sq_AL") && !formDescAL.equals("")){ %>
								<%= formDescAL%>
							<%}else{ %>
								<liferay-ui:message key="leadmodal.description" />
							<%} %>
						</h5>						
					</div>
					<div class="col-12">
						<form name="fm" id="leadSales-modal" action="${currentPageUrl}" method="post">
							<input type="hidden" name="portletAction" value="submitLeadModalAction"/>
							<input type="hidden" id="currentUrl" name="currentUrl"
								   value="<%=currentUrl%>" />
							<input type="hidden" id="showSurvey" name="showSurvey"
								value="<%=showSurvey %>"/>
							<%
								if (userType != null && userType.equals("business")) {
							%>
								<div class="floating-label-wrap">
								<input type="text" id="fullName" name="fullName"
									class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
									placeholder="<liferay-ui:message key="leadform.contact.person"/> *"> 
									<label for="fullName" class="floating-label"><liferay-ui:message key="leadform.contact.person" /> *</label>
									<small></small>
								</div>
								<div class="floating-label-wrap">
									<input type="text" id="companyName" name="companyName"
										class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
										placeholder="<liferay-ui:message key="leadform.companyName"/> *">
									<label for="companyName" class="floating-label"><liferay-ui:message key="leadform.companyName" /> *</label>
									<small></small>
								</div>
							<%
								}else{
							%>
								<div class="floating-label-wrap">
									<input type="text" id="fullName" name="fullName"
										class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
										placeholder="<liferay-ui:message key="leadform.fullname"/> *">
									<label for="fullName" class="floating-label"><liferay-ui:message key="leadform.fullname" /> *</label>
									<small></small>
								</div>
							<%} %>


							<div class="row">

								<div class="col-12 col-sm-6">
									<div class="floating-label-wrap">
										<input type="tel" id="phone" name="phone"
											defaultValue="389"
											value="389" oninput="checkNumberFieldLength(this, 11);"
											class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2 keep-default-value"
											placeholder="<liferay-ui:message key="leadform.msisdn"/> 389xxxxxxxx *"
											title="389xxxxxxxx"> <label for="phone"
											class="floating-label"><liferay-ui:message
												key="leadform.msisdn" /> 389xxxxxxxx *</label>
										<small></small>
									</div>
								</div>
								<div class="col-12 col-sm-6">
									<div class="floating-label-wrap">
										<input type="email" id="email" name="email"
											class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
											placeholder="<liferay-ui:message key="leadform.email"/> *"> <label for="email"
											class="floating-label"><liferay-ui:message
												key="leadform.email" /> *</label>
										<small></small>
									</div>

								</div>
							</div>
							<input type="hidden" id="installationAddress" value="<%= installationAddress%>"/>
							<%
								if (installationAddress != null && installationAddress.equals("installationAddress")) {
							%>
							
							<div class="floating-label-wrap">
								<input type="text" id="instAddress" name="instAddress"
									
									class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
									placeholder="<liferay-ui:message
										key="leadform.installationAddress" /> *"
									> <label for="instAddress"
									class="floating-label"><liferay-ui:message
										key="leadform.installationAddress" /> *</label>
								<small></small>

							</div>
							<%}
							%>
							<!-- row -->
							<div class="row">
								<div class="col-12">

									<div class="floating-label-wrap">
										<textarea id="comment" name="comment"
											class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
											maxlength="200" rows="6"
											placeholder="<liferay-ui:message key="leadform.comment" />"></textarea>
										<label for="comment" class="floating-label"><liferay-ui:message
												key="leadform.comment" /></label>
									</div>

								</div>
								
								<%
									if (agreePersonalData != null && agreePersonalData.equals("agreePersonalData")) {
								%>
								<div class="col-12 mb-3" id="card-personal-data">
                               		<input id="personal-data" name="personal-data" type="checkbox" class="form-checkbox" />	                    
                               		<label for="personal-data" class="form-checkboxLabel personal-data">
       									<liferay-ui:message key="leadform.agree.personal.data"/>
   									</label>
   									<small></small>
                               	</div>
								<%} %>
							


								<div class="col-12">
									<span><liferay-ui:message key="leadform.required.fields" /></span>
								</div>

							</div>
							<!-- row -->

							<!-- Hp field -->
							<div class="hp-field">
								<label for="website">Website:</label>
								<input type="text" name="website" id="website" autocomplete="off">
							</div>


							<button id="leadModalSubmitBtn" type="submit"
								class="btn btn-primary btn-lg margin-top-20">
								<liferay-ui:message key="leadform.button.send" />
							</button>
						</form>
					</div>
					<!-- col-12 -->
				</div>
				<!--/.row-->
			</div>
			<!--/modal-body-->
		</div>
		<!--/modal-content-->
	</div>
</div>
<!--/.modal-->

<script type="text/javascript">

	let leadModalForm = document.querySelector('#leadSales-modal');

	let fullNameEl = document.querySelector('#fullName');
	let companyNameEl = document.querySelector('#companyName');
	let msisdnEl = document.querySelector('#phone');
	let emailEl = document.querySelector('#email');
	let instAddressEl = document.querySelector('#instAddress');

	let msisdnFormat = "389";

	function checkName(nameEl){
		let valid = false;
		let name = nameEl.value.trim();
		if (!isRequired(name)) {
			showError(nameEl, Liferay.Language.get('a1.form.validation.required-field'));
		}else{
			showSuccess(nameEl);
			valid = true;
		}
		return valid;
	}

	function checkInstallationAddress () {
		let valid = false;
		let address = instAddressEl.value.trim();
		if (!isRequired(address)) {
			showError(instAddressEl, Liferay.Language.get('a1.form.validation.required-field'));
		}else{
			showSuccess(instAddressEl);
			valid = true;
		}
		return valid;
	}

	function checkPersonalDataCheckbox() {
		let valid = false;
		if($('#personal-data').length > 0){
			if ($('#personal-data').is(':checked')) {
				$("label.personal-data").css("color","#373f45");
				$("#card-personal-data").css("color","#373f45");
				valid = true;
			}else{
				$("label.personal-data").css("color","#e70028");
				$("#card-personal-data").css("color","#e70028");
			}
		}else{
			valid = true;
		}
		return valid;
	}

	$('#personal-data').on('change', function() {
		if ($('#personal-data').is(':checked')) {
			$("label.personal-data").css("color","#373f45");
			$("#card-personal-data").css("color","#373f45");
		}else{
			$("label.personal-data").css("color","#e70028");
			$("#card-personal-data").css("color","#e70028");
		}
	});

	leadModalForm.addEventListener('submit', function (e) {
		e.preventDefault();

		let website = this.querySelector('input[name="website"]').value;
		if(website == ""){
			let isFullNameValid = checkName(fullNameEl),
					isMsisdnValid = checkMsisdn(msisdnEl, msisdnFormat),
					isEmailValid = checkEmail(emailEl);

			let companyNameValid = $('#companyName').val() == undefined || checkName(companyNameEl);

			let installationAddressValid = $('#instAddress').val() == undefined || checkInstallationAddress();
			let personalDataCheckboxValid = checkPersonalDataCheckbox();

			let isFormValid = isFullNameValid &&
					companyNameValid &&
					isMsisdnValid &&
					isEmailValid &&
					installationAddressValid &&
					personalDataCheckboxValid;
			if (isFormValid) {
				leadModalForm.submit();
			}
		}

	});

	let debounce = (fn, delay = 500) => {
		let timeoutId;
		return (...args) => {
			// cancel the previous timer
			if (timeoutId) {
				clearTimeout(timeoutId);
			}
			// setup a new timer
			timeoutId = setTimeout(() => {
				fn.apply(null, args)
			}, delay);
		};
	};

	leadModalForm.addEventListener('input', debounce(function (e) {
		switch (e.target.id) {
			case 'fullName':
				checkName(fullNameEl);
				break;
			case 'companyName':
				checkName(companyNameEl);
				break;
			case 'phone':
				checkMsisdn(msisdnEl, msisdnFormat);
				break;
			case 'email':
				checkEmail(emailEl);
				break;
			case 'instAddress':
				checkInstallationAddress();
				break;
		}
	}));

</script>