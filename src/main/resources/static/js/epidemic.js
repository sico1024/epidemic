function navChange(obj){
    n1.style.color = '#b2ceff';
    n2.style.color = '#b2ceff';
    n3.style.color = '#b2ceff';
    n4.style.color = '#b2ceff';
    obj.style.color = '#fff';

}

$(function () {
    var n1 = document.getElementById('n1');
    var n2 = document.getElementById('n2');
    var n3 = document.getElementById('n3');
    var n4 = document.getElementById('n4');

    var chinaEpidemic = document.getElementById('chinaEpidemic');
    var development = document.getElementById('development');
    var trend = document.getElementById('trend');
    var hospital = document.getElementById('hospital');

    n1.onclick = function () {
        navChange(this);
        chinaEpidemic.style.display = 'block';
        development.style.display = 'none';
        trend.style.display = 'none';
        hospital.style.display = 'none';
    };

    n2.onclick = function () {
        navChange(this);
        trend.style.display = 'block';
        chinaEpidemic.style.display = 'none';
        development.style.display = 'none';
        hospital.style.display = 'none';
    };

    n3.onclick = function () {
        navChange(this);
        development.style.display = 'block';
        chinaEpidemic.style.display = 'none';
        trend.style.display = 'none';
        hospital.style.display = 'none';
    };

    n4.onclick = function () {
        navChange(this);
        development.style.display = 'none';
        chinaEpidemic.style.display = 'none';
        trend.style.display = 'none';
        hospital.style.display = 'block';
    };


});

$(function () {

    var citys = document.getElementsByClassName('city');
    for(var i=0; i<citys.length;i++){
        citys[i].onclick = function () {
            var ul = $(this).next();
            var symbol = $(this).children('.city-symbol');

            if(this.value!=1){
                ul.css('display','block');
                symbol.html('▲');

                this.value = 1;
            }else{
                ul.css('display','none');
                symbol.html('▼');
                this.value= 0;
            }
        }
    }
});

$(function () {

    var switchChina = document.getElementById("switch-china");
    var switchForeign = document.getElementById("switch-foreign");
    var china = document.getElementById("china");
    var foreign = document.getElementById("foreign");

    switchChina.onclick = function () {
        china.style.display = 'block';
        foreign.style.display = 'none';
        this.style.opacity = 1;
        this.style.color = '#005dff';
        switchForeign.style.opacity = 0.7;
        switchForeign.style.color = '#000';
    }

    switchForeign.onclick = function () {
        foreign.style.display = 'block';
        china.style.display = 'none';

        this.style.opacity = 1;
        this.style.color = '#005dff';
        switchChina.style.opacity = 0.7;
        switchChina.style.color = '#000';

    }



});