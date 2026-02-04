<%@include file="/admin-init.jsp"%>

<%
    List<SchoolLeadModel> tempResults = (List<SchoolLeadModel>) request.getAttribute("schoolLeads");
%>

<portlet:renderURL var="filterURL">
    <portlet:param name="jspPage" value="/admin/schoolLead/view.jsp" />
</portlet:renderURL>

<script type="text/javascript">
    function submitMyForm(action, method) {
        var elem = document.getElementById("filterFormFinderField");
        var frm = elem.form;
        frm.action = action;
        frm.method = method;
        frm.submit();
    }
</script>


<div class="container mt-5">
    <div class="row">
        <div class="col-12 col-sm-12 col-md-10 offset-md-1">
            <aui:fieldset-group markupView="lexicon">
                <aui:fieldset>
                    <aui:form action="/o/LeadSales-portlet/api/downloadSchoolLeads/csv" method="get" enctype="multipart/form-data">
                        <input type="hidden" id="filterFormFinderField" />
                        <input type="hidden" name="downloadId" value="clasid6354asdf" />

                        <liferay-ui:message key="from"/>
                        <div class="aui-datepicker aui-helper-clearfix" name="from" id="#<portlet:namespace />startDatePicker">
                            <input type="text"
                                   name="startDate"
                                   id="<portlet:namespace />startDate"
                                   size="30" autocomplete="off"
                                   value="<%=ParamUtil.getString(request, "startDate","")%>"/>
                        </div>
                        <liferay-ui:message key="to"/>
                        <div class="aui-datepicker aui-helper-clearfix" name="to" id="#<portlet:namespace />endDatePicker">
                            <input type="text"
                                   name="endDate"
                                   id="<portlet:namespace />endDate"
                                   size="30"
                                   autocomplete="off"
                                   value="<%=ParamUtil.getString(request, "endDate","")%>"/>
                        </div>

                        <div style="margin-top:10px">
                            <aui:button-row>
                                <aui:button type="submit" value="Download CSV"/>
                            </aui:button-row>
<%--                            <aui:button value="Download as CSV" align="right"--%>
<%--                                        onClick="javascript:submitMyForm('/o/LeadSales-portlet/downloadSchoolLeads','get')" />--%>
                        </div>
                    </aui:form>
                    </br>
                    <liferay-ui:search-container
                            emptyResultsMessage="there-are-no-data" delta="20">
                        <liferay-ui:search-container-results>
                            <%
                                results = ListUtil.subList(tempResults,
                                        searchContainer.getStart(),
                                        searchContainer.getEnd());
                                total = tempResults.size();
                                searchContainer.setTotal(total);
                                searchContainer.setResults(results);
                            %>
                        </liferay-ui:search-container-results>
                        <liferay-ui:search-container-row
                                className="com.iwmn.a1.lead.model.jpa.SchoolLeadModel" keyProperty="id"
                                modelVar="m">

                            <liferay-ui:search-container-column-text name="School Name"
                                                                     property="schoolName" />
                            <liferay-ui:search-container-column-text name="City"
                                                                     property="city" />
                            <liferay-ui:search-container-column-text name="Contact Person"
                                                                     property="contactPerson" />
                            <liferay-ui:search-container-column-text name="Phone Number"
                                                                     property="phoneNumber" />
                            <liferay-ui:search-container-column-text name="Email"
                                                                     property="email" />
                            <liferay-ui:search-container-column-text name="Person Type"
                                                                     property="personType" />
                            <liferay-ui:search-container-column-text name="Creation Date"
                                                                     property="creationDate" />
                            <liferay-ui:search-container-column-text name="IP"
                                                                     property="ip" />
                        </liferay-ui:search-container-row>
                        <liferay-ui:search-iterator />
                    </liferay-ui:search-container>
                </aui:fieldset>
            </aui:fieldset-group>
        </div>
    </div>
</div>

<aui:script>
    AUI().use('aui-datepicker', function(A) {
    new A.DatePicker({
    trigger : '#<portlet:namespace/>startDate',
    mask: '%d/%m/%Y',
    popover : {
    zIndex : 1
    }
    });

    new A.DatePicker({
    trigger : '#<portlet:namespace/>endDate',
    mask: '%d/%m/%Y',
    popover : {
    zIndex : 1
    }
    });
    });
</aui:script>