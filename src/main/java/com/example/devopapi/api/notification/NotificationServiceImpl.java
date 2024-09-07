package com.example.devopapi.api.notification;

import com.example.devopapi.api.dto.CreateNotificationDto;
import com.example.devopapi.api.dto.NotificationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private final RestTemplate restTemplate;

    @Value("${onesignal.rest.api.key}")
    private String oneSignalApiKey;

    @Value("${onesignal.app.id}")
    private String oneSignalAppId;
    @Override
    public boolean pushNotification(CreateNotificationDto notificationDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("Authorization","Basic "+ oneSignalApiKey);
        headers.set("content-type","application/json");

        NotificationDto body = NotificationDto.builder()
                .appId(oneSignalAppId)
                .includedSegments(notificationDto.includedSegments())
                .contents(notificationDto.contents())
                .build();

        HttpEntity<NotificationDto> requestBody = new HttpEntity<>(body, headers);

        ResponseEntity<?> responses = restTemplate.postForEntity("https://api.onesignal.com/notifications?c=push", requestBody, Map.class);
        System.out.println(responses.getStatusCode());
        System.out.println(responses.getBody());
//        OkHttpClient client = new OkHttpClient(); This is original code of Notification
//
//        MediaType mediaType = MediaType.parse("application/json");
//        RequestBody body = RequestBody.create(mediaType, "{\"app_id\":\"string\",\"name\":\"string\",\"included_segments\":[\"string\"],\"include_aliases\":\"string\",\"filters\":[{\"field\":\"string\",\"key\":\"string\",\"relation\":\"string\",\"value\":\"string\"}],\"contents\":{\"en\":\"string\",\"es\":\"string\"},\"headings\":{\"en\":\"string\",\"es\":\"string\"},\"subtitle\":{\"en\":\"string\",\"es\":\"string\"},\"template_id\":\"string\",\"content_available\":false,\"mutable_content\":true,\"target_content_identifier\":\"string\",\"data\":\"string\",\"url\":\"string\",\"app_url\":\"string\",\"web_url\":\"string\",\"ios_attachments\":\"string\",\"chrome_web_image\":\"string\",\"big_picture\":\"string\",\"adm_big_picture\":\"string\",\"chrome_big_picture\":\"string\",\"huawei_big_picture\":\"string\",\"huawei_msg_type\":\"string\",\"huawei_category\":\"string\",\"huawei_bi_tag\":\"string\",\"buttons\":\"string\",\"web_buttons\":\"string\",\"ios_category\":\"string\",\"icon_type\":\"string\",\"android_channel_id\":\"string\",\"huawei_channel_id\":\"string\",\"existing_android_channel_id\":\"string\",\"huawei_existing_channel_id\":\"string\",\"small_icon\":\"string\",\"huawei_small_icon\":\"string\",\"large_icon\":\"string\",\"huawei_large_icon\":\"string\",\"adm_small_icon\":\"string\",\"adm_large_icon\":\"string\",\"chrome_web_icon\":\"string\",\"chrome_web_badge\":\"string\",\"firefox_icon\":\"string\",\"chrome_icon\":\"string\",\"ios_sound\":\"string\",\"wp_wns_sound\":\"string\",\"android_accent_color\":\"string\",\"huawei_accent_color\":\"string\",\"ios_badgeType\":\"string\",\"ios_badgeCount\":0,\"apns_alert\":\"string\",\"android_group\":\"string\",\"adm_group\":\"string\",\"adm_group_message\":\"string\",\"collapse_id\":\"string\",\"web_push_topic\":\"string\",\"thread_id\":\"string\",\"summary_arg\":\"string\",\"summary_arg_coun\":0,\"ios_relevance_score\":0,\"ios_interruption_level\":\"string\",\"ttl\":0,\"priority\":0,\"enable_frequency_cap\":false,\"apns_push_type_override\":\"string\",\"isIos\":false,\"isAndroid\":false,\"isHuawei\":false,\"isAnyWeb\":false,\"isChromeWeb\":false,\"isFirefox\":false,\"isSafari\":false,\"isWP_WNS\":false,\"isAdm\":false,\"isChrome\":false}");
//        Request request = new Request.Builder()
//                .url("https://api.onesignal.com/notifications?c=push")
//                .post(body)
//                .addHeader("accept", "application/json")
//                .addHeader("Authorization", "Basic YOUR_ONESIGNAL_API_KEY")
//                .addHeader("content-type", "application/json")
//                .build();
//
//        Response response = client.newCall(request).execute();
        if(responses.getStatusCode() == HttpStatus.OK){
            return true;
        }else {
            return false;
        }
    }
}
