<%@include file="/init-front.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@include file="./messages.jsp"%>

<%
    ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    String currentUrl = td.getURLCurrent();

    String lang = themeDisplay.getLocale().toString();
    PortletPreferences prefs = renderRequest.getPreferences();
    String userType = prefs.getValue("userType", "private");
    String styleType = prefs.getValue("styleType", "style1");
    String backgroundImagePath = prefs.getValue("backgroundImagePath", "");

    String formTitle="";
    String formDescription="";
    String buttonCityLabel="";
    String buttonAdrressLabel="";
    String buttonMsisdnLabel="";
    String buttonSubmitName="";

    if(lang!=null && lang.equals("en_US")){
        formTitle = prefs.getValue("formTitleEN", "");
        formDescription = prefs.getValue("formDescriptionEN", "");
        buttonCityLabel = prefs.getValue("buttonCityLabelEN", "");
        buttonAdrressLabel = prefs.getValue("buttonAdrressLabelEN", "");
        buttonMsisdnLabel = prefs.getValue("buttonMsisdnLabelEN", "");
        buttonSubmitName = prefs.getValue("buttonSubmitNameEN", "");
    }else if(lang!=null && lang.equals("sq_AL")){
        formTitle = prefs.getValue("formTitleAL", "");
        formDescription = prefs.getValue("formDescriptionAL", "");
        buttonCityLabel = prefs.getValue("buttonCityLabelAL", "");
        buttonAdrressLabel = prefs.getValue("buttonAdrressLabelAL", "");
        buttonMsisdnLabel = prefs.getValue("buttonMsisdnLabelAL", "");
        buttonSubmitName = prefs.getValue("buttonSubmitNameAL", "");
    }else{
        formTitle = prefs.getValue("formTitleMK", "");
        formDescription = prefs.getValue("formDescriptionMK", "");
        buttonCityLabel = prefs.getValue("buttonCityLabelMK", "");
        buttonAdrressLabel = prefs.getValue("buttonAdrressLabelMK", "");
        buttonMsisdnLabel = prefs.getValue("buttonMsisdnLabelMK", "");
        buttonSubmitName = prefs.getValue("buttonSubmitNameMK", "");
    }
%>

<%if(backgroundImagePath!=null && !backgroundImagePath.isEmpty()) {%>
<style>
    #internetAvailabilityWrapper{
        padding: 40px 0 40px 0;
        background-image: url("<%= backgroundImagePath%>");
    }
</style>
<%} %>

<div id="internetAvailabilityWrapper">
    <div id="errorMsg" style="display:none;">
        <section class="d-flex flex-column align-items-center justify-content-center">
            <%if(styleType!=null && styleType.equals("style2")){ %>
            <div class="dostapnost-dark">
                    <%}else{ %>
                <div class="container bg-white">
                    <%} %>
                    <div class="row">
                        <div class="col-12">
                            <div class="text-center">
                                <svg fill="#fff" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="100"><title>A1_warning_icon</title><g id="Background"><rect class="cls-1" width="32" height="32"/></g><g id="Icons"><rect fill="#f2f2f2" x="15" y="11" width="2" height="6"/><rect fill="#f2f2f2" x="15" y="19" width="2" height="2"/><path fill="#f2f2f2" d="M16,26A10,10,0,1,1,26,16,10,10,0,0,1,16,26ZM16,8a8,8,0,1,0,8,8A8,8,0,0,0,16,8Z"/></g></svg>
                                <h2 class="font-secondary my-3 text-red"><liferay-ui:message key="internetAvailability.error.title"/></h2>
                                <h3 class="font-secondary"><liferay-ui:message key="internetAvailability.error.description"/></h3>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </div>
    <div id="successMsg" style="display:none;">
        <section class="d-flex flex-column align-items-center justify-content-center">
            <%if(styleType!=null && styleType.equals("style2")){ %>
            <div class="dostapnost-dark">
                    <%}else{ %>
                <div class="container bg-white">
                    <%} %>
                    <div class="row">
                        <div class="col-12">
                            <div class="text-center">
                                <svg fill="#76c318" id="Icons" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="100"><title></title><polygon points="30 8 28 6 12 20 4 14 2 16 12 26 30 8" /></svg>
                                <h2 class="font-secondary my-3"><liferay-ui:message key="internetAvailability.success.title"/></h2>
                                <h3 class="font-secondary"><liferay-ui:message key="internetAvailability.success.description"/></h3>
                            </div>
                        </div>
                    </div>
                </div>
        </section>
    </div>
        <%if(styleType!=null && styleType.equals("style2")){ %>
    <div class="dostapnost-dark" id="internetAvailabilityContent">
        <%}else{ %>
        <div class="container bg-white" id="internetAvailabilityContent">
            <%} %>
            <div class="row" style="padding:30px;">
                <%if(styleType!=null && styleType.equals("style2")){ %>
                <div class="col-3 d-none d-md-block mt-4 text-right">
                    <img src="<%=request.getContextPath()%>/images/internet_availability_map.png" class="img-fluid">
                </div>
                <%}else{ %>
                <div class="col-3 d-none d-md-block mt-4 text-right">
                    <img src="<%=request.getContextPath()%>/images/internet_availability_map.png" class="img-fluid">
                </div>
                <%} %>

                <div class="col-12 col-md-9 margin-top-30">
                    <h2 class="font-secondary my-3">
                        <%if (formTitle!=null && !formTitle.equals("")){%>
                        <%= formTitle%>
                        <%}else if(userType!=null && userType.equals("business")){ %>
                        <liferay-ui:message key="internetAvailability.form.title.business" />
                        <%}else{ %>
                        <liferay-ui:message key="internetAvailability.form.title" />
                        <%} %>
                    </h2>
                    <p>
                        <%if (formDescription!=null && !formDescription.equals("")){%>
                        <%= formDescription%>
                        <%}else if(userType!=null && userType.equals("business")){ %>
                        <liferay-ui:message key="internetAvailability.form.description.business" />
                        <%}else{ %>
                        <liferay-ui:message key="internetAvailability.form.description" />
                        <%} %>
                    </p>
                    <div class="row">
                        <div class="col-12">
                            <form action="${currentPageUrl}" method="post" id="internetAvailabilityForm">
                                <input type="hidden" name="portletAction" value="submitAvailabilityInternetServicesFormAction"/>
                                <input type="hidden" id="currentUrl" name="currentUrl" value="<%=currentUrl%>" />
                                <input type="hidden" id="userType" name="userType" value="<%=userType %>" />
                                <input type="hidden" id="cityCyrilic" name="cityCyrilic" value="" />
                                <div class="form-row">
                                    <div class="col-12 col-xl-3">
                                        <div class="floating-label-wrap mb-3">
                                            <select class="form-control form-control-lg" id="city" name="city">

                                                <%if (buttonCityLabel!=null && !buttonCityLabel.equals("")){%>
                                                <option ><%= buttonCityLabel%></option>
                                                <%}else if(userType!=null && userType.equals("business")){ %>
                                                <option ><liferay-ui:message key="city.business"/></option>
                                                <%}else{ %>
                                                <option><liferay-ui:message key="city"/></option>
                                                <%} %>
                                                <option></option>
                                                <c:forEach var="item" items="${cityList }">
                                                    <option value="${item.getNameByLocale('en_US') }" data-city-cyrilic="${item.getNameByLocale('mk_MK') }">${item.getNameByLocale(lang) }</option>
                                                </c:forEach>
                                            </select>
                                            <small></small>
                                        </div>
                                    </div>
                                    <div class="col-12 col-xl-3">
                                        <div class="floating-label-wrap mb-3">
                                            <%if (buttonAdrressLabel!=null && !buttonAdrressLabel.equals("")){%>
                                            <input name="address"  id="address" type="text"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                   placeholder="<%= buttonAdrressLabel %>"/>
                                            <label for="address" class="floating-label"><liferay-ui:message key="<%= buttonAdrressLabel %>"/></label>
                                            <%}else if(userType!=null && userType.equals("business")){ %>
                                            <input name="address"  id="address" type="text"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                   placeholder="<liferay-ui:message key="internetAvailability.form.enter.address.business"/>"/>
                                            <label for="address" class="floating-label"><liferay-ui:message key="internetAvailability.form.enter.address.business"/></label>
                                            <%}else{ %>
                                            <input name="address"  id="address" type="text"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                   placeholder="<liferay-ui:message key="internetAvailability.form.enter.address"/>"/>
                                            <label for="address" class="floating-label"><liferay-ui:message key="internetAvailability.form.enter.address"/></label>
                                            <%} %>
                                            <small></small>
                                        </div>
                                    </div>
                                    <div class="col-12 col-xl-3">
                                        <div class="floating-label-wrap mb-3">
                                            <%if (buttonMsisdnLabel!=null && !buttonMsisdnLabel.equals("")){%>
                                            <input name="msisdn" id="msisdn" type="tel"
                                                   value="07" defaultvalue="07" oninput="checkNumberFieldLength(this, 9);"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3 keep-default-value"
                                                   placeholder="<%= buttonMsisdnLabel%>"/>
                                            <label for="msisdn" class="floating-label"><%= buttonMsisdnLabel%></label>
                                            <%}else if(userType!=null && userType.equals("business")){ %>
                                            <input name="msisdn" id="msisdn" type="tel"
                                                   value="07" defaultvalue="07" oninput="checkNumberFieldLength(this, 9);"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3 keep-default-value"
                                                   placeholder="<liferay-ui:message key="internetAvailability.form.msisdn.business"/>"/>
                                            <label for="msisdn" class="floating-label"><liferay-ui:message key="internetAvailability.form.msisdn.business"/></label>
                                            <%}else{ %>
                                            <input name="msisdn" id="msisdn" type="tel"
                                                   value="07" defaultvalue="07" oninput="checkNumberFieldLength(this, 9);"
                                                   class="form-control form-control-lg floating-label-field floating-label-field--s3 keep-default-value"
                                                   placeholder="<liferay-ui:message key="internetAvailability.form.msisdn"/>"/>
                                            <label for="msisdn" class="floating-label"><liferay-ui:message key="internetAvailability.form.msisdn"/></label>
                                            <%} %>
                                            <small></small>
                                        </div>
                                    </div>
                                    <!-- Hp field -->
                                    <div class="hp-field">
                                        <label for="website">Website:</label>
                                        <input type="text" name="website" id="website" autocomplete="off">
                                    </div>
                                    <div class="col-auto">
                                        <button class="btn btn-primary btn-lg">
                                            <%if (buttonSubmitName!=null && !buttonSubmitName.equals("")){%>
                                            <%=buttonSubmitName %>
                                            <%}else if(userType!=null && userType.equals("business")){ %>
                                            <liferay-ui:message key="internetAvailability.form.btn.send.business"/>
                                            <%}else{ %>
                                            <liferay-ui:message key="internetAvailability.form.btn.send"/>
                                            <%} %>
                                        </button>
                                    </div>
                                </div>
                                <div class="form-row ">
                                    <div class="col-12 mt-4 mt-md-0 mb-3">
                                        <small>
                                            <liferay-ui:message key="internetAvailability.form.bottom.description"/>
                                        </small>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">

        $( document ).ready(function() {

            let isMobile = window.matchMedia("only screen and (max-width: 760px)").matches;
            if (isMobile) {

                $('#city').find('option').get(0).remove();
                //Conditional script here
                var buttonCityLabelString = '<%=buttonCityLabel%>';
                var userTypeString = '<%=userType%>';
                if(buttonCityLabelString!=null && buttonCityLabelString !== ""){
                    $("#city").select2({
                        placeholder : buttonCityLabelString
                    });
                }else if(userTypeString!=null && userTypeString === "business"){
                    $("#city").select2({
                        placeholder : "<liferay-ui:message key='city.business'/>"
                    });
                }else{
                    $("#city").select2({
                        placeholder : "<liferay-ui:message key='city'/>"
                    });
                }
            } else{
                $('#city').find('option').get(1).remove();
            }

            const form = document.querySelector('#internetAvailabilityForm');

            const cityEl = document.querySelector('#city');
            const addressEl = document.querySelector('#address');
            const msisdnEl = document.querySelector('#msisdn');

            const msisdnFormat = "07";

            const debounce = (fn, delay = 500) => {
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

            function checkCityDropdown(inputEl){
                let valid = false;
                const inputValue = $('#city').find(":selected").attr('value');
                if($('#city').find(":selected").attr('value')==undefined || !isRequired(inputValue)){
                    showError(inputEl, Liferay.Language.get('a1.form.validation.required-field'));
                } else {
                    showSuccess(inputEl);
                    valid = true;
                }
                return valid;
            }
            function checkInput(inputEl){
                let valid = false;
                const inputValue = inputEl.value.trim();
                if (!isRequired(inputValue)) {
                    showError(inputEl, Liferay.Language.get('a1.form.validation.required-field'));
                } else {
                    showSuccess(inputEl);
                    valid = true;
                }
                return valid;
            }

            function checkAddress () {
                let valid = false;
                const address = addressEl.value.trim();
                if (!isRequired(address)) {
                    showError(addressEl, Liferay.Language.get('a1.form.validation.required-field'));
                }else{
                    showSuccess(addressEl);
                    valid = true;
                }
                return valid;
            }

            $("#internetAvailabilityForm").submit(function(event) {
                event.preventDefault();
                let website = this.querySelector('input[name="website"]').value;
                if(website === "") {
                    let isCityValid = checkCityDropdown(cityEl),
                    //isAdressValid = checkAddress(),
                        isMsisdnValid = checkMsisdn(msisdnEl, msisdnFormat);
                    let isFormValid = isCityValid &&
                        //isAdressValid &&
                        isMsisdnValid;

                    // submit to the server if the form is valid
                    if (isFormValid) {
                        $('#cityCyrilic').val($('#city').find(':selected').attr('data-city-cyrilic'));
                        form.submit();
                        //document.querySelector('#internetAvailabilityForm').submit();
                    }
                }
            });

            form.addEventListener('input', debounce(function (e) {
                switch (e.target.id) {
                    case 'city':
                        checkCityDropdown(cityEl);
                        break;
// 	        case 'address':
// 	        	checkAddress();
// 	            break;
                    case 'msisdn':
                        checkMsisdn(msisdnEl, msisdnFormat);
                        break;
                }
            }));

        });
    </script>
