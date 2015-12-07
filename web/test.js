$("input[name='ParseSelect']").change(function(e){
    if ($(this).val() == 'fullPath1') {
        //noinspection JSUnresolvedFunction
        $(".param1").prop("value", "");
        //noinspection JSUnresolvedFunction
        $(".param1").prop("checked", false);
        //noinspection JSUnresolvedFunction
        $(".param1").prop("disabled", true);
        //noinspection JSUnresolvedFunction
        $(".test").prop("disabled", false);

    }else { //noinspection JSUnresolvedFunction
        if($(this).val() == 'selectAttrib') {
                //noinspection JSUnresolvedFunction
            $(".test").prop("value", "");
                $(".test").prop("disabled", true);
                $(".param1").prop("disabled", false);
            }
    }
});