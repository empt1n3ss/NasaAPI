import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaApiResponse  {
    private String date;
    private String explanation;
    private String media_type;
    private String service_version;
    private String title;
    private String url;

    public NasaApiResponse(
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.date = date;
        this.explanation = explanation;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getDate(){
        return date;
    }
    public String getExplanation(){
        return explanation;
    }
    public String getMedia_type(){
        return media_type;
    }
    public String getService_version(){
        return service_version;
    }
    public String getTitle(){
        return title;
    }
    public String getUrl(){
        return url;
    }
    @Override
    public String toString(){
        return
                "\n id=" + date+
                        "\n explanation=" + explanation+
                        "\n media_type=" + media_type+
                        "\n service_version=" + service_version+
                        "\n title=" + title+
                        "\n url=" + url;
    }
}
