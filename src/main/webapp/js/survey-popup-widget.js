const scriptTag = document.getElementById('customScript');

// Get the src attribute which contains the URL with parameters
const src = scriptTag.src;

// Parse the query parameters from the URL using URLSearchParams
const urlParams = new URLSearchParams(src.split('?')[1]); // Get the part after the "?"

// Access specific parameters
const Mobile_Number = urlParams.get('Mobile_Number');
const address = urlParams.get('address');

/*
* run VOC DIGI Survey from console
* open opco page and copy paste in inspect->console
*/

var A1group={};

/* ***********************************
* FOR TESTING CHANGE ONLY THIS PART *
* *********************************** */

A1group.opco= 'a1mk';                 // your OPCO
A1group.survey_id = '38';      // your SurveyId
A1group.customer_id = '';       // Customer ID (if you are able to identify)
A1group.language = 'mk';
A1group.metadata =  {
    'Mobile_Number': Mobile_Number,
    'Country_Code': 'MK',
    'address': address,
    'Agent_ID': '',
    'OPCO': 'MK',
    'Brand': 'A1',
    'Online_Event': '',
    'Business_Touchpoint_ID': '',
    'TAG_Custom_Field_1': '',
    'TAG_Custom_Field_2': '',
    'TAG_Custom_Field_3': '',
    'TAG_Custom_Field_4': '',
    'TAG_Custom_Field_5': ''
};

/* ***********************************
* FOR TESTING CHANGE ONLY THIS PART *
* *********************************** */

A1group.opcos= {
    a1at : 'cloud.email.a1.net',
    a1rs : 'cloud.email.a1.rs',
    a1hr : 'cloud.e.a1.hr',
    a1mk : 'cloud.e.a1.mk',
    a1si : 'cloud.email.a1.si',
    a1by : 'cloud.email.a1.by',
    a1bg : 'cloud.email.a1.bg'
};

A1group.scriptSrc = 'https://' + A1group.opcos[A1group.opco] +'/voc_digi.js?cssUrl=https://' + A1group.opcos[A1group.opco] + '/voc_digi.css&tagManagerIntegration=true';

A1group.scriptSrcCheck = 'voc_digi.js';
A1group.scripts = Array.prototype.slice.call(document.getElementsByTagName('script'), 0).map(function (x) {return x.src || '';});

if (!(A1group.scripts.join('|||').indexOf(A1group.scriptSrcCheck) !== -1)){
    var runner = document.createElement('script');
    runner.setAttribute('src', A1group.scriptSrc);
    document.head.appendChild(runner);
}

function sleep(ms) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

await sleep(2*1000);


window.VocSignal_SendEvent('SetParameters', {
    apiUrl: 'https://exokube.eu/bls-' + A1group.opco +   '/sms-survey/surveys/interaction',
    customerId: A1group.customer_id,
    language: A1group.language,
    surveyId: A1group.survey_id,
    metadata: A1group.metadata
});

window.VocSignal_SendEvent('PopupSurvey');


