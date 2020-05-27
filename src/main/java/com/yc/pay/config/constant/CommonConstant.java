package com.yc.pay.config.constant;

/**
 * 功能描述：全局常量
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-21
 * @Version: 1.0.0
 */
public class CommonConstant {

    // =============== 基础常量 ====================
    /**
     * 成功码
     */
    public static final int SUCCESS_CODE = 200;

    // ================== 编码格式 ===========================
    /**
     * 编码格式
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

    /**
     * 编码格式
     */
    public static final String CHARSET_GBK = "GBK";

    //================= 加解密 ====================
    /**
     * MD5
     */
    public static final String ENCODE_MD5 = "MD5";

    /**
     * AES
     */
    public static final String ENCODE_AES = "AES";

    /**
     * RSA
     */
    public static final String ENCODE_RSA = "RSA";

    /**
     * SHA1PRNG 算法
     */
    public static final String ALGORITHM_SHA1PRNG = "SHA1PRNG";

    // ================= 公钥/私钥 ===============================
    /**
     * aliPC支付公钥(对数据加密)
     */
    public static final String ALi_PC_PAY_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtojdtkETo4OEsQLeyyPwtWK9ZqYJANq6jjXC74vk9n/r88yW577y7VdxcK9X/F/wvR7D8of7lndYdhg6xZro0eO2skPZTU+A549J7tfzahVbIBAS+x1WPFJwPtVrfBBvkwHL8PT+YnMcxKyBxOa6wo8fzJs1NgU1+qnDCpwUFyv59GUfdzBvTPL1fY3ZzvRHFHbapevVltbO/jNV0thb8dafmcJXl8lnjQy3XlH3eTH28tlVfqickacfRl/WSD8WN3dGgF7dTDKYfSR7YB7jsHe6VzoHM3UnD9/yQbi/Z3ZrL7yOxEjq4tfrKlZIW7ZCoUpOU4QdPIRhLeC6nWyGrQIDAQAB";

    /**
     * aliPC支付私钥(对数据解密)
     */
    public static final String ALi_PC_PAY_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDUcbUUBaTJA4ngFx1xED2WHrkS4YCi3BRDPu/qU+MXj7VNy/ip+VKSK9aGPd2dRswkt/4DOoOxnnesJDcve+2W9y2gPoOdSVjGlEwTE6MqeB5f3l+RO9Kcb2zL4JwCRxlE0HHLeRdWtnmbelh1rg8zTFwGnoi1pbQajAT/FGDFqIpdI6FrEYbyeR/VNxFXIGK16Z7gfnWRnS4TP93O5ckcuaAcE8tqW16G7u41bKsnsv2149mucBM2hFSEVD/KcGYYke3pD7VpQdk0+WhxITSsa5I8DfSFRKokN7iJoKjYWjI4gN3fxFWLbymeIumoOWdJflwYI5oL+GdVIHfQ0ETLAgMBAAECggEBALaqurdnjuQkfcXIOlGAVGQjMKFycmgWcfnMQQAsdyRINe2Zx8tnDL+QoBm3UjmsqVWdOvVNt/TevCmwzh6vIYBgMsQJXKO+cG33D16L0Q1wUTW/gE7hsFtAV70J+TrgJXMNA/ufuBigN/oe/bbaHknOi4ZJhGUkALOe16D4xajNjHcYdvkfw5Zkv9nAt+GjFphe1lt35KIx6ahFzMcnzzHYy4ezDFdQWPkT7CqrL8Nh9KPm6Sjbzw2JP3RH91OU1q44gPj7yyLTs1oxt4gEqOgVnczJWXoH+eKRmxRoIvodnB4kf1W9LTzAc4Qs7OneDccvhrOoTD3MGhqW0poXZFkCgYEA6dxBCLnU5oeoctoltnh42DXCzEv/M/gkv9HMY/FFnuM6e0qU5EcFjQtTPRRW1S45ctZaHHhpGppHF7RG+okdALj4x2OC1NG9DL4HboVW3CJ4TzmEeZRkR+LWRWw7FA8eXefDBTC4ojAwqkmbvYP3dm6FjSfNBGYs43Wm6qKTcB0CgYEA6I5rPmAv6rB1BBXF8riNhuoDntbHFHOC9kCwuVJooDBBnxXNCqA9mTdnwE8hjgCgbO5lQkeeIRYiZwFYdCLPW78j6Np5CaX0/ZxcVSUQBFYc3d0e3po+rqpX6BGOj7qGhzDXvAs+HScu7ERy0Kbq6b/EbuLlR3oCKIGncGIaxAcCgYBJyWrjm+6mxgrKIjZf+mb2oQ/Tce8VsKe3tjRtHEVBOqTLHd8Yn6gKtpYO4Yn8PVd2+lb4QK247RCdVA5JIlX6UmJ8VtOC3qJtkM+7eWrMjjuzk4xO6Bkz7Uh6IwoI7DRCoMuRqau30MiqEguHoknEHl8ZCIPRbYOgSRDfW2h1qQKBgF9ODnFPphN+IVZ9PdRNAeMqgDVWO9wLwr38oPAx76LGY/44RwF1zgi+hgxv4YZ6h0RdJq5U/1773TltebyOj4BAAw1oi3YCxzYwID7co4XDbK0X85CykcGvGbuHhm8st/krcR4lVV1JM5esLYmI/nixGGWBIwl53OyQxffunJ19AoGBAOX9cBWFIWh5EHVXYvW90HRBQoo1AYtsdrxTedqrij6cWQms6ACeQZPd5O0V6/Lhz3Lw5NRnUl9MbzwOC7BBSBvhWwNzBOzFnaoOvBABa5nFZsdF5jSI3LJHbnxmMvxnKjCgogSGf+hfgR042b/WKDDBEA965MQJ59tCVhALD81z";


    /**
     * RSA公钥(对数据加密)
     */
    public static final String RSA_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAitE3SxGmSvx2tv226rN9/mUPe8G5jsIcBJz98/IPg4m4ML7J0VbXTryxMZKTXETuAOVTQJnA3yoHa17dtJ5xiEwTG9WR0GtwnTONRwjkLZ+NydfXphh6ykxDfXlnyytVdKExpisNTEm5zy0F7A1lcYhG134G3pPjlqueYq67yyDfUfxYmMZdP89l2GiFq0Q2pkSOYf2Uw4MyWfb9Lt+dJoOs9RimR+/4ApbtKFchBWbdNUYKOcWHSO5f6BfI1XRCdOvqhB5U9v+FOrHsUXKzM89H9la8iYN6B9/lxYQZ5090Wx1tlBV/9PtUYEYvbAlduVgMa+3VDbyFbb0IRAMn0wIDAQAB";

    /**
     * RSA私钥(对数据解密)
     */
    public static final String RSA_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCK0TdLEaZK/Ha2" +
            "/bbqs33+ZQ97wbmOwhwEnP3z8g+DibgwvsnRVtdOvLExkpNcRO4A5VNAmcDfKgdrXt20nnGITBMb1ZHQa3CdM41HCOQtn43J19emGHrKTEN9eWfLK1V0oTGmKw1MSbnPLQXsDWVxiEbXfgbek+OWq55irrvLIN9R/FiYxl0/z2XYaIWrRDamRI5h/ZTDgzJZ9v0u350mg6z1GKZH7/gClu0oVyEFZt01Rgo5xYdI7l/oF8jVdEJ06+qEHlT2/4U6sexRcrMzz0f2VryJg3oH3+XFhBnnT3RbHW2UFX/0+1RgRi9sCV25WAxr7dUNvIVtvQhEAyfTAgMBAAECggEAaF4d/VG6vhwMQU6FzPXAX5ipyHkBQsTL/efuww8V/OZ5ViTLZZmt7SPO8R9rrW6hpojETAiNSvu1clNzys1pPHk90KqEo7dzhq1wpZyYuXiBSOT/3IABKcGm6Fpz3/unlzVuN6mcqOOxUINgNXZo86/q2y+EiKmV7fz/6t/Gj1P/CEH3fBfhwNg+VQFArIpx7YdzqAysEx7At/91CVTKOM8FkfH+clRy6j2lNm+6mo6ERcxVC+Tz9FUlat28HGI/uVFx2wAdtZZYVo5NH5kH1BUo+dYb3llfY7Mxq2s4N8Hf84WUf3pzimWiYZYLkYD2Own/KEwTrHAcJinAIBWLWQKBgQDDwJfpNclN7/l1HtPRyalY90+UOe/mM06TIYOOmt2afcVyyNRjkqve6f63QuSbf4e2FGeE9svTgle8EZh74KPtAjevST8SXtsAhUxUznbmHk2JqYSuDjSG5Qkf5AgCqc8V8I4VCZu6WSfRcOgLtYXAUAE7BMGelqd1q0jOLPSUFwKBgQC1iq9Uh9VZ3+CAf/mPajH11m+XTa7c0f9jplNJdQRwZB/QKx2RPK2Q+CFBl00uVcVOT8F/wiQ6gqFVbo9EQiMfy8KMpE4vvcC+WoirKrs2jY4absM4OblLIfPjCkqFoUwFIPeuwrpjhyxLR3tfv/JMA018WwuLF7rGiuYJ+u4TpQKBgB5qY3A6CkBQ5Drdgl4bbnPDYZsPcYYcPL5Bu9ZBm+MwXaibKd0a4het1gUVoL3EkAqrmvYe/sarfwRa242L9wyRhqN2xxUMpgyrqZOXzQOue6/DJjmBPYokNkm9Pb1kMygei4UbTBYlWjmByJxhJRoflEFeWNNSZ0yyQ4q9k3cbAoGBALBJc+iJ3svfcopVu2lgnhMzoDk03lDgOzfxE3+Jej4JfDsRIy0d1w2nZePjoLcV4vsgx63gQH3wy+nB6q1F7AW8P7eC9cm2Yax3Da/pVtKqswtH85pts1kaJ1KZc7Q/5So10U6LGaP8Zy7SwyM/qXHW4y+W2aa/7EdQKgIXAYlhAoGBALqYWSBjMkpUrtkjkDoaPJl1OsrxZ+CiEw/OKdcT6zS5qf/NQ2yTzjcwge2N4Oaj9pBBHR3MYQ+6DyUgnNxbKpsr09X4aE5Siod3e+Lm+11fd9SWCjSOUJJJEm2Z3OpKkUDolXfXVRpRP3I1ibIPwEIz+N8WllvSRP2C9iRysGqJ";



}
