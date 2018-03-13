package pl.desz.scrapper;

/**
 * Creates payload {@link OtodomSearchQueryBuilder#payload} with
 * given parameters to search. Dedicated to Otodom.pl site.
 */
public class OtodomSearchQueryBuilder {

    enum Balcony {

        YES("balcony"), OPTIONAL("");

        private String queryDesc;

        Balcony(String desc) {
            this.queryDesc = desc;
        }

        @Override
        public String toString() {
            return this.queryDesc;
        }
    }

    private static String payload = "search[order]=created_at_first:desc&view=&min_id=&search[street_id]=" +
            "&search[district_id]=&search[city_id]=167&search[subregion_id]=311&search[region_id]=12" +
            "&search[dist]=0&search[select_all]=&search[category_id]=101&search[filter_float_price:from]=" +
            "&search[filter_float_price:to]=&search[filter_float_m:from]=%(minFlatArea)&search[filter_float_m:to]=%(maxFlatArea)" +
            "&search[filter_float_price_per_m:from]=&search[filter_float_price_per_m:to]=" +
            "&search[filter_float_building_floors_num:from]=&search[filter_float_building_floors_num:to]=" +
            "&search[filter_float_build_year:from]=&search[filter_enum_rooms_num][]=%(roomsCount)&search[filter_float_build_year:to]=&search[created_since]=" +
            "&search[id]=&q=&search[description]=1&search[filter_enum_extras_types][]=%(balcony)";

    /**
     * minimal area of flat in meters square
     */
    private int minFlatArea;
    private int maxFlatArea;
    private int roomsCount;
    private Balcony balcony = Balcony.OPTIONAL;

    private OtodomSearchQueryBuilder() {
        // intended
    }


    public static OtodomSearchQueryBuilder newBuilder() {
        return new OtodomSearchQueryBuilder();
    }

    public OtodomSearchQueryBuilder setMinFlatArea(Integer minFlatArea) {
        this.minFlatArea = minFlatArea;
        return this;
    }

    public OtodomSearchQueryBuilder setMaxFlatArea(int maxFlatArea) {
        this.maxFlatArea = maxFlatArea;
        return this;
    }

    public OtodomSearchQueryBuilder setRooms(int roomsCount) {
        this.roomsCount = roomsCount;
        return this;
    }

    public OtodomSearchQueryBuilder setBalcony(Balcony balcony) {
        this.balcony = balcony;
        return this;
    }

    public String build() {
        payload = payload.replace("%(minFlatArea)", (this.minFlatArea == 0) ? "" : String.valueOf(this.minFlatArea));
        payload = payload.replace("%(maxFlatArea)", (this.maxFlatArea == 0) ? "" : String.valueOf(this.maxFlatArea));
        payload = payload.replace("%(roomsCount)", (this.roomsCount == 0) ? "" : String.valueOf(this.roomsCount));
        payload = payload.replace("%(balcony)", (this.balcony == null) ? Balcony.OPTIONAL.toString() : this.balcony.toString());

        return payload;
    }
}
