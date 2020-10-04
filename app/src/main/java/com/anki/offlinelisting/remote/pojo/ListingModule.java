package com.anki.offlinelisting.remote.pojo;

import com.anki.offlinelisting.local.entity.Member;

import java.util.ArrayList;
import java.util.List;
/**
 * Pojo class for Member listing API
 */
public class ListingModule {
    public List<Member> getResults() {
        return results;
    }

    public void setResults(List<Member> results) {
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    private List<Member> results;
    private Info info;

    public class Info{
        private String seed;
        private int results;
        private int page;
        private String version;

        public String getSeed() {
            return seed;
        }

        public void setSeed(String seed) {
            this.seed = seed;
        }

        public int getResults() {
            return results;
        }

        public void setResults(int results) {
            this.results = results;
        }

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }
    }
}
