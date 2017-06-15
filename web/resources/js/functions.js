$(document).ready(function () {

    var $transferType = $('#transfer-type');
    var $wherefromBlock = $('#wherefrom-block');
    var $domainBlock = $('#domain-block');

    var income = 'INCOME';
    var expense = 'EXPENSE';


    showWherefromDomainBlock();
    $transferType.change(showWherefromDomainBlock);

    function showWherefromDomainBlock () {
        if ($transferType.val() === income) {
            $wherefromBlock.show();
            $domainBlock.hide();
        }
        else if ($transferType.val() === expense) {
            $domainBlock.show();
            $wherefromBlock.hide();
        }
    }

    $('select').material_select();

    $('.datepicker').pickadate({
        selectMonths: true,
        selectYears: 15,
        format: "mm/dd/yyyy",
        closeOnSelect: true,
        closeOnClear: true,
        min: '01/01/2010',
        max: new Date()
    });

});