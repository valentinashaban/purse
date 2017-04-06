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

});