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

    // ================== 编码格式 ===========================
    /** 文本编码 */
    public static String TEXT_CONTENTTYPE = "text/plain;charset=UTF-8";

    /** JSON编码 */
    public static String JSON_CONTENTTYPE = "application/json;charset=UTF-8";

    /** XML编码 */
    public static String XML_CONTENTTYPE = "text/xml;charset=UTF-8";

    /**
     * 编码格式
     */
    public static final String CHARSET_UTF_8 = "UTF-8";

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
            "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCC/tk1oqymli5HpP9MxOkNofKkON1a6EqKg/S0++mZoKLInLasLkNuz0F8nSg/nBZGMv8XcIOXeOsK1ZT9tibxlgzun22lnBpHLueCAZlqITx33XO8SwiVULar2NIVFJwuUdvHPf/2Wj361cTd+zw+uJTPy3vIBljeOHDajzqBg9m5JrCIOgUze+4MZBT5/74gwwwpMDhRHRfR+4FTFmxqqzbTBGaFv4gCLSRB/3VpuruAP5AKCTR2ATHXgFO9veCemNwx5sI3F9Tw43+16xzAZh2T/6Xb3d31H9k31h9Iai7t2s00qY6eKsFpdi4ySK5qB2G9lbFvbpBv+3gz/5UFAgMBAAECggEANWn7Yc5yPf8w4c/atg/4w7FMgkAZqm7brj1+M6ogp58DoW+dYPoATxe9qTVf6wgXYQp8T692SPZqSOGsPgPfP1Ui8s4ZZJURdgMfLTi/uLHSuUGYZqfl2RSvV8UybJwZzx1b05NpYqeLbFNsUeOWvjB/pXdggsBrzjkPc19ByPtERzjiEFzvxGdYPDdvgNUmvbQj647X8umdyDAcNAbd8rkSQW87zBT12OQ/i/QR74BBg4VBJqTg9APk9mdVNufvxMwi9+lyGsGRFdnjmPCLHJvnT3Nh9KSHWuLrOeUjkGVEozd40iz/8LVwr+BZNeL6EjtZ3ztfphnIl92lMFNhQQKBgQDlm0t5N0REM0al/L0BygkwLsHHvAmjYhzjDJeooLgH0NyYYP/ruqqF6oPxDDaw17pciEypyOdoQ1JFaw9Uq41PfZlqL74PehSD3KWe7pPzlWIukaDq1aPit/pox92+ZO0lqwHQa80Tf1PO55xAVJQPltrhScnAmZiVIG1TD9z+sQKBgQCSDbGW2lVx5b9s1R0l8SRvoHQc83iIIyqx/+UJEEwxEMwSJ4ygAfUPcrJOYs2gOB9Ny08xqtNEQKlghwgwFqIec705AiU67Bw3TjWHnxgzvhzLPCrty/yJbWMmpXDjMm/VvwASkBL6pijjh/GUrRsJvh0McgWkDIg8OgIRu5rYlQKBgQChJ+nGb7sTn2XT9Vv4GIVwczEB7wJY0fFyj5EXA4+HtNpQfazDGOa9TchD9Q2h/BjK/8PHW8LIVJA3NxiwliR+CasXc+ET3dzuXH1G2y+vRUd/ZimrCj6YUAeLadVC4HXu/WMtlayAJdt+GuR55qNxebGxdOgNrgEBkpwJM9YqUQKBgCvFcNit/HeTDEiYohCx8WKG6uWWTiQ2reAEueZ6fOsjhpVWRv3ZOFF15Vw6njeLOk59RPG0qXZGDr0AGwMWdWW8+BOyweejxV0J0l8f3gf7zPNXx+HWhYvGPbXiVS+x+PRNNr9ZcGawD4cJQex16KmF0XzeWzRsERRDqkUYPXL1AoGBAJVzao5Irdt0wj/R8NG3uVeEMbESNLKNLZ7M9hPaZn2xacoPHXXLKg2m546RYNOngYLB5l9dbpake5BoXtuGylmtD1tZvGSxLmznfrxQpZr0vuR2iCIgsVGdPhJlIRbKyeocofsRdaXgD413O4MCi/WmJ4E1ydyOqv+2cCQQAJBB";

    /**
     * aliPC支付私钥(对数据解密)
     */
    public static final String ALi_PC_PAY_PRIVATE_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtojdtkETo4OEsQLeyyPwtWK9ZqYJANq6jjXC74vk9n/r88yW577y7VdxcK9X/F/wvR7D8of7lndYdhg6xZro0eO2skPZTU+A549J7tfzahVbIBAS+x1WPFJwPtVrfBBvkwHL8PT+YnMcxKyBxOa6wo8fzJs1NgU1+qnDCpwUFyv59GUfdzBvTPL1fY3ZzvRHFHbapevVltbO/jNV0thb8dafmcJXl8lnjQy3XlH3eTH28tlVfqickacfRl/WSD8WN3dGgF7dTDKYfSR7YB7jsHe6VzoHM3UnD9/yQbi/Z3ZrL7yOxEjq4tfrKlZIW7ZCoUpOU4QdPIRhLeC6nWyGrQIDAQAB";


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
