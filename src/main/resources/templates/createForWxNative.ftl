<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>微信支付</title>
</head>
<body>
<div id="myQrcode"></div>
<div id="orderNo" hidden>${orderNo}</div>
<div id="returnUrl" hidden>${returnUrl}</div>

<script src="https://cdn.bootcss.com/jquery/1.5.1/jquery.min.js"></script>
<script src="https://cdn.bootcss.com/jquery.qrcode/1.0/jquery.qrcode.min.js"></script>
<script>
    jQuery('#myQrcode').qrcode({
        text	: "${codeUrl}"
    });

    $(function() {
        setInterval(function () {
            console.log('开始查询支付状态...')
            $.ajax({
                'url': '/payInfo/payInfo',
                data: {
                    'orderNo': $('#orderNo').text()
                },
                success: function (result) {
                    if (result.payState != null
                        && result.payState === 'SUCCESS') {
                        window.open($('#returnUrl').text(),"_self")
                    }
                },
                error: function (result) {
                    alert(result)
                }
            })
        },2000)
    })
</script>
</body>
</html>
