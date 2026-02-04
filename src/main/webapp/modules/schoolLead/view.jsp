<%@include file="/init-front.jsp"%>

<jsp:include page="/modules/schoolLead/error.jsp" />

<% String lang = themeDisplay.getLocale().toString(); %>
<div class="modal fade vip-modal" id="popup-wrap-modal" data-hash="popup-wrap-modal">
    <div class="modal-dialog">
        <div class="modal-content" style="margin: 0 auto;">
            <div id="netflixWrapper" class="dostapnost-dark">
                <button type="button" class="close" data-dismiss="modal">
                    <i class="fa fa-times" aria-hidden="true"></i>
                </button>
                <div class="modal-body">
                    <div id="errorMsg" style="display:none;">
                        <section class="d-flex flex-column align-items-center justify-content-center">
                            <div class="row">
                                <div class="col-12">
                                    <div class="text-center">
                                        <svg fill="#fff" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="100"><title>A1_warning_icon</title><g id="Background"><rect class="cls-1" width="32" height="32"/></g><g id="Icons"><rect fill="#f2f2f2" x="15" y="11" width="2" height="6"/><rect fill="#f2f2f2" x="15" y="19" width="2" height="2"/><path fill="#f2f2f2" d="M16,26A10,10,0,1,1,26,16,10,10,0,0,1,16,26ZM16,8a8,8,0,1,0,8,8A8,8,0,0,0,16,8Z"/></g></svg>
                                        <h2 class="font-secondary my-3 text-red"><liferay-ui:message key="internetAvailability.error.title"/></h2>
                                        <h3 class="font-secondary"><liferay-ui:message key="internetAvailability.error.description"/></h3>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                    <div id="successMsg" style="display:none;">
                        <section class="d-flex flex-column align-items-center justify-content-center">
                            <div class="row">
                                <div class="col-12">
                                    <div class="text-center">
                                        <svg fill="#76c318" id="Icons" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 32 32" width="100"><title></title><polygon points="30 8 28 6 12 20 4 14 2 16 12 26 30 8" /></svg>
                                        <h2 class="font-secondary my-3"><liferay-ui:message key="internetAvailability.success.title"/></h2>
                                        <h3 class="font-secondary"><liferay-ui:message key="internetAvailability.success.description"/></h3>
                                    </div>
                                </div>
                            </div>
                        </section>
                    </div>
                    <div id="schoolLeadContent">
                        <div class="row">
                            <div class="col-12 mb-4">
                                <h2 class="font-secondary-bold text-center">
                                    <liferay-ui:message key="schoolLead.form.title" />
                                </h2>
                                <p class="text-center">
                                    <liferay-ui:message key="schoolLead.form.description" />
                                </p>
                                <div class="row">
                                    <div class="col-10 col-offset-1 mt-4">
                                        <form action="${currentPageUrl}" method="post" id="schoolLeadForm">
                                            <input type="hidden" name="portletAction" value="submitSchoolLeadAction"/>
                                            <div class="form-row">
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <input type="text" id="schoolName" name="schoolName"
                                                               class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                               placeholder="<liferay-ui:message key="schoolLead.form.schoolName"/> *">
                                                        <label for="schoolName" class="floating-label"><liferay-ui:message key="schoolLead.form.schoolName"/> *</label>
                                                        <small></small>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <select class="form-control form-control-lg" id="city" name="city">
                                                            <option value=""><liferay-ui:message key="city"/></option>
                                                            <c:forEach var="item" items="${cityList }">
                                                                <option value="${item.getNameByLocale('en_US') }">${item.getNameByLocale(lang) }</option>
                                                            </c:forEach>
                                                        </select>
                                                        <small></small>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <input type="text" id="contactPerson" name="contactPerson"
                                                               class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                               placeholder="<liferay-ui:message key="schoolLead.form.contact.person"/> *">
                                                        <label for="contactPerson" class="floating-label"><liferay-ui:message key="schoolLead.form.contact.person"/> *</label>
                                                        <small></small>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <input name="msisdn" id="msisdn" type="tel"
                                                               value="07" defaultvalue="07" oninput="checkNumberFieldLength(this, 9);"
                                                               class="form-control form-control-lg floating-label-field floating-label-field--s3 keep-default-value"
                                                               placeholder="<liferay-ui:message key="internetAvailability.form.msisdn"/>"/>
                                                        <label for="msisdn" class="floating-label"><liferay-ui:message key="internetAvailability.form.msisdn"/></label>
                                                        <small></small>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <input type="text" id="email" name="email"
                                                               class="form-control form-control-lg floating-label-field floating-label-field--s3"
                                                               placeholder="Email *">
                                                        <label for="email" class="floating-label">Email *</label>
                                                        <small></small>
                                                    </div>
                                                </div>
                                                <div class="col-12">
                                                    <div class="floating-label-wrap mb-3">
                                                        <select class="form-control form-control-lg" id="personType" name="personType">
                                                            <option value=""><liferay-ui:message key="schoolLead.form.person.type"/></option>
                                                            <option value="dete"><liferay-ui:message key="schoolLead.form.person.type.dete"/></option>
                                                            <option value="roditel"><liferay-ui:message key="schoolLead.form.person.type.roditel"/></option>
                                                            <option value="nastavnik"><liferay-ui:message key="schoolLead.form.person.type.nastavnik"/></option>
                                                        </select>
                                                        <small></small>
                                                    </div>
                                                </div>
                                            </div>

                                            <!-- Hp field -->
                                            <div class="hp-field">
                                                <label for="website">Website:</label>
                                                <input type="text" name="website" id="website" autocomplete="off">
                                            </div>

                                            <div class="form-row text-center">
                                                <div class="col-12">
                                                    <button class="btn btn-primary btn-lg">
                                                        <liferay-ui:message key="internetAvailability.form.btn.send"/>
                                                    </button>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                                <div class="row text-center mt-3">
                                    <div class="col-12 mt-4 mt-md-0 mb-3">
                                        <small>
                                            <liferay-ui:message key="internetAvailability.form.bottom.description"/>
                                        </small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!--#netflixContent -->
                </div>
                <!--/modal-body-->
            </div>
            <!-- /#netflixWrapper -->
        </div>
        <!--/modal-content-->
    </div>
    <!--/modal-dialog-->
</div>
<!--/.modal-->
<script>
    $( document ).ready(function() {
        let schoolLeadForm = document.querySelector('#schoolLeadForm');

        let schoolNameEl = document.querySelector('#schoolName');
        let cityEl = document.querySelector('#city');
        let contactPersonEl = document.querySelector('#contactPerson');
        let msisdnEl = document.querySelector('#msisdn');
        let emailEl = document.querySelector('#email');
        let personTypeEl = document.querySelector('#personType');
        let msisdnFormat = "07";

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

        function checkInput(inputEl){
            let valid = false;
            let inputValue = inputEl.value.trim();
            if (!isRequired(inputValue)) {
                showError(inputEl, Liferay.Language.get('a1.form.validation.required-field'));
            } else {
                showSuccess(inputEl);
                valid = true;
            }
            return valid;
        }

        schoolLeadForm.addEventListener('submit', function (e) {
            e.preventDefault();

            let website = this.querySelector('input[name="website"]').value;
            if (website === "") {
                let isSchoolNameValid = checkInput(schoolNameEl),
                    isCityValid = checkInput(cityEl),
                    isContactPersonValid = checkInput(contactPersonEl),
                    isMsisdnValid = checkMsisdn(msisdnEl, msisdnFormat),
                    isEmailValid = checkEmail(emailEl),
                    isPersonTypeValid = checkInput(personTypeEl);

                let isFormValid = isSchoolNameValid && isCityValid && isContactPersonValid && isMsisdnValid && isEmailValid && isPersonTypeValid;

                // submit to the server if the form is valid
                if (isFormValid) {
                    schoolLeadForm.submit();
                }
            }
        });

        schoolLeadForm.addEventListener('input', debounce(function (e) {
            switch (e.target.id) {
                case 'schoolName':
                    checkInput(schoolNameEl);
                    break;
                case 'city':
                    checkInput(cityEl);
                    break;
                case 'contactPerson':
                    checkInput(contactPersonEl);
                    break;
                case 'msisdn':
                    checkMsisdn(msisdnEl, msisdnFormat);
                    break;
                case 'email':
                    checkEmail(emailEl);
                    break;
                case 'personType':
                    checkInput(personTypeEl);
                    break
            }
        }));
    });
    $( ".showSchoolLead" ).on( "click", function() {
        $("#schoolLeadContent").show();
        $("#successMsg").hide();
        $("#errorMsg").hide();
        $('#popup-wrap-modal').modal();
    } );
</script>

