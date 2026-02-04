<%@include file="/init-front.jsp"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ page import="com.liferay.portal.kernel.theme.ThemeDisplay" %>

<%
    ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
    String currentUrl = td.getURLCurrent();
    String lang = themeDisplay.getLocale().toString();
    PortletPreferences prefs = renderRequest.getPreferences();
    String formDescMK = prefs.getValue("formDescMK", "");
    String formDescAL = prefs.getValue("formDescAL", "");
    String formDescEN = prefs.getValue("formDescEN", "");
    String showSurvey = prefs.getValue("showSurvey", "false");
%>

<jsp:include page="/modules/leadForm/error.jsp" />

<div class="container">
    <div id="leadForm" class="margin-top-20">
        <div class="row">
            <div class="col-12 col-md-8 offset-md-2 col-lg-6 offset-lg-3">
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
                            <liferay-ui:message key="leadform.description" />
                            <%} %>
                        </h5>
                    </div>
                    <!-- /col-12 -->
                    <form name="fm" id="leadSales-form" action="${currentPageUrl}" method="post">
                        <input type="hidden" name="portletAction" value="submitLeadFormAction"/>
                        <input type="hidden" id="currentUrl" name="currentUrl"
                               value="<%=currentUrl%>" />
                        <input type="hidden" id="showSurvey" name="showSurvey"
                               value="<%=showSurvey %>"/>
                        <div class="col-12">
                            <div class="floating-label-wrap">
                                <input type="text" id="fullName" name="fullName"
                                       class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
                                       placeholder="<liferay-ui:message key="leadform.fullname"/> *">
                                <label for="fullName" class="floating-label"><liferay-ui:message key="leadform.fullname" /> *</label>
                                <small></small>
                            </div>

                            <div class="row">
                                <div class="col-12 col-sm-6">
                                    <div class="floating-label-wrap">
                                        <input type="tel" id="phone" name="phone"
                                               defaultValue="3897"
                                               value="3897" oninput="checkNumberFieldLength(this, 11);"
                                               class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2 keep-default-value"
                                               placeholder="<liferay-ui:message key="leadform.msisdn"/> 3897xxxxxxx *"
                                               title="3897xxxxxxx">
                                        <label for="phone" class="floating-label"><liferay-ui:message
                                            key="leadform.msisdn" /> 3897xxxxxxx *</label>
                                        <small></small>

                                    </div>
                                </div>
                                <div class="col-12 col-sm-6">
                                    <div class="floating-label-wrap">
                                        <input type="email" id="email" name="email"
                                               class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
                                               placeholder="<liferay-ui:message key="leadform.email"/> *">
                                        <label for="email" class="floating-label"><liferay-ui:message key="leadform.email"/> *</label>
                                        <small></small>
                                    </div>
                                </div>

                            </div>

                            <div class="floating-label-wrap">
                                    <textarea id="comment" name="comment" class="form-control form-control-lg mt-2 floating-label-field floating-label-field--s3 mb-2"
                                              maxlength="200" rows="6"
                                              placeholder="<liferay-ui:message key="leadform.comment" />"></textarea>
                                <label for="comment"
                                       class="floating-label"><liferay-ui:message key="leadform.comment" /></label>
                            </div>
                        </div>
                        <!--/.col-12-->

                        <!-- Hp field -->
                        <div class="hp-field">
                            <label for="website">Website:</label>
                            <input type="text" name="website" id="website" autocomplete="off">
                        </div>


                        <div class="col-12">
                            <span><liferay-ui:message key="leadform.required.fields" /></span>
                        </div>

                        <div class="row">
                            <div class="col-12">
                                <button id="leadModalSubmitBtn" type="submit"
                                        class="btn btn-primary btn-lg margin-top-20">
                                    <liferay-ui:message key="leadform.button.send" />
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script type="text/javascript">

    let leadForm = document.querySelector('#leadSales-form');

    let fullNameEl = document.querySelector('#fullName');
    let msisdnEl = document.querySelector('#phone');
    let emailEl = document.querySelector('#email');

    let msisdnFormat = "3897";

    let isNameValid = (name) => {
        let re = /[а-яA-Za-z0-9]*$/;
        return re.test(name);
    };

    function checkName(nameEl){
        let valid = false;
        let name = nameEl.value.trim();
        if (!isRequired(name)) {
            showError(nameEl, Liferay.Language.get('a1.form.validation.required-field'));
        }else if(!isNameValid(name)){
            showError(nameEl, Liferay.Language.get('a1.form.validation.name.not.valid'));
        }else{
            showSuccess(nameEl);
            valid = true;
        }
        return valid;
    }

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

    leadForm.addEventListener('submit', function (e) {
        // prevent the form from submitting
        e.preventDefault();

        let website = this.querySelector('input[name="website"]').value;
        if (website === "") {
            let isFullNameValid = checkName(fullNameEl),
                isMsisdnValid = checkMsisdn(msisdnEl, msisdnFormat),
                isEmailValid = checkEmail(emailEl);

            let isFormValid = isFullNameValid &&
                isMsisdnValid &&
                isEmailValid;

            if (isFormValid) {
                leadForm.submit();
            }
        }

    });

    leadForm.addEventListener('input', debounce(function (e) {
        switch (e.target.id) {
            case 'fullName':
                checkName(fullNameEl);
                break;
            case 'phone':
                checkMsisdn(msisdnEl, msisdnFormat);
                break;
            case 'email':
                checkEmail(emailEl);
                break;
        }
    }));
</script>