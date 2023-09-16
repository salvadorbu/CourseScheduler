package com.example.CourseScheduler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

public class RegistrationTimetable {
    private String termYear;
    private URL url;
    private HttpURLConnection connection;


    public RegistrationTimetable(String termYear) throws IOException {
        this.termYear = termYear;
        url = new URL("https://apps.es.vt.edu/ssb/HZSKVTSC.P_ProcRequest");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");

        connection.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.9");
        connection.setRequestProperty("Cache-Control", "max-age=0");
        connection.setRequestProperty("Connection", "keep-alive");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("Cookie", "JSESSIONID=70EBA74121238C0161EDFB80140E67D9; TESTID=set; SESSID=VkRBMEROMTI5MDc5ODM=; _gcl_au=1.1.1740623690.1688754846; _ga_SD43QX7XJF=GS1.1.1690136574.4.1.1690136592.0.0.0; style=default; _fbp=fb.1.1692900375999.608525575; _ga_D99RC0R2WH=GS1.1.1693423786.1.1.1693423819.0.0.0; _ga_QEQ6BRGQ8X=GS1.2.1693423823.1.1.1693423824.0.0.0; _ga_MT0L4G1S43=GS1.1.1693423855.2.1.1693423863.0.0.0; _ga_ZNSTZ2YGVJ=GS1.1.1693423874.1.1.1693423882.0.0.0; _ga_JH204F297Y=GS1.1.1693423976.2.1.1693424066.0.0.0; _ga_ZJEQ62D8H3=GS1.1.1693423797.1.1.1693424371.0.0.0; _ga_28REPJ3J7C=GS1.1.1693423797.1.1.1693424371.0.0.0; _ga_1HMZZXCML9=GS1.1.1693877982.1.0.1693878977.0.0.0; _ga_4M1PDYJDXD=GS1.1.1694049830.1.0.1694049835.0.0.0; _gid=GA1.2.1017126560.1694378263; _ga_JHMTZTGLR9=GS1.1.1694408671.5.1.1694408673.0.0.0; _ga_6EG7YSHYLZ=GS1.1.1694544289.1.0.1694544292.57.0.0; _ga_6NH85V357P=GS1.2.1694544304.1.0.1694544304.0.0.0; _ga_3KYJBSR9WZ=GS1.1.1694544304.2.0.1694544309.0.0.0; _hp2_id.3001039959=%7B%22userId%22%3A%221452475606665952%22%2C%22pageviewId%22%3A%221294729460907501%22%2C%22sessionId%22%3A%22470442909988332%22%2C%22identity%22%3Anull%2C%22trackerVersion%22%3A%224.0%22%7D; _ga_VPVQ2Q69QH=GS1.2.1694821088.73.1.1694821094.0.0.0; _ce.s=v~08beacc3bb8f4fb898f060a84b714492daa4cf7e~vpv~7~lcw~1694544290120~lcw~1694838284544; _ce.clock_event=1; _ce.clock_data=112%2C45.3.77.115%2C1%2C3357fadb0316939352bbdd4d5360a97f; _ga_5Z60EH83Q9=GS1.1.1694838284.18.0.1694838287.57.0.0; _ga_DTEQ1JM2SG=GS1.1.1694838284.18.0.1694838287.0.0.0; X-Route-ssomgr-prod=da92159da296dc64; _ga=GA1.1.199173471.1670260661; X-Route-ssb-ords-banner-prod=d70935a1f68ffb91; _ga_T9PY1ZDFJ5=GS1.1.1694842957.52.1.1694842963.0.0.0; __utmc=15161830; __utmz=15161830.1694842964.10.5.utmcsr=login.vt.edu|utmccn=(referral)|utmcmd=referral|utmcct=/; __utma=15161830.199173471.1670260661.1694842964.1694845305.11; __utmt=1; IDMSESSID=CD055517DDC8D682A7AA53AFB3B9242980ED175685F9C74EB1665E6C9C1FDA3F11714A5A59F349D7FEEA00CCCD80075F5AAAEACF0105CFC3419924F04671DC6F; __utmb=15161830.5.10.1694845305");
        connection.setRequestProperty("Origin", "https://apps.es.vt.edu");
        connection.setRequestProperty("Referer", "https://apps.es.vt.edu/ssb/HZSKVTSC.P_ProcRequest");
        connection.setRequestProperty("Sec-Fetch-Dest", "document");
        connection.setRequestProperty("Sec-Fetch-Mode", "navigate");
        connection.setRequestProperty("Sec-Fetch-Site", "same-origin");
        connection.setRequestProperty("Sec-Fetch-User", "?1");
        connection.setRequestProperty("Upgrade-Insecure-Requests", "1");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/116.0.0.0 Safari/537.36");
        connection.setRequestProperty("sec-ch-ua", "\"Chromium\";v=\"116\", \"Not)A;Brand\";v=\"24\", \"Google Chrome\";v=\"116\"");
        connection.setRequestProperty("sec-ch-ua-mobile", "?0");
        connection.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");

        connection.setDoOutput(true);
    }

    public void getCourses(String subject, String subjectID) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
        String payload = String.format("CAMPUS=0&TERMYEAR=%s&CORE_CODE=AR%25&" +
                "subj_code=%s&SCHDTYPE=%25&CRSE_NUMBER=%s&crn=&open_only=&" +
                "disp_comments_in=Y&sess_code=%25&BTN_PRESSED=FIND+class+sections&inst_name=", termYear, subject, subjectID);

        writer.write(payload);
        writer.flush();
        writer.close();
        connection.getOutputStream().close();

        InputStream responseStream = connection.getResponseCode() / 100 == 2
                ? connection.getInputStream()
                : connection.getErrorStream();
        Scanner s = new Scanner(responseStream).useDelimiter("\\A");
        String response = s.hasNext() ? s.next() : "";
        System.out.println(response);
    }
}
