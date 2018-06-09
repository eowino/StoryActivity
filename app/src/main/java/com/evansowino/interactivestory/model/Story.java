package com.evansowino.interactivestory.model;

import com.evansowino.interactivestory.R;

import java.util.ArrayList;

public class Story {

    private ArrayList<Page> pages;

    public Story() {
        pages = new ArrayList<>();
        pages.add(new Page(R.drawable.page0,
                R.string.page0,
                new Choice(R.string.page0_choice1, 1),
                new Choice(R.string.page0_choice2, 2)));

        pages.add(new Page(R.drawable.page1,
                R.string.page1,
                new Choice(R.string.page1_choice1, 3),
                new Choice(R.string.page1_choice2, 4)));

        pages.add(new Page(R.drawable.page2,
                R.string.page2,
                new Choice(R.string.page2_choice1, 4),
                new Choice(R.string.page2_choice2, 6)));

        pages.add(new Page(R.drawable.page3,
                R.string.page3,
                new Choice(R.string.page3_choice1, 4),
                new Choice(R.string.page3_choice2, 5)));

        pages.add(new Page(R.drawable.page4,
                R.string.page4,
                new Choice(R.string.page4_choice1, 5),
                new Choice(R.string.page4_choice2, 6)));

        pages.add(new Page(R.drawable.page5, R.string.page5));

        pages.add(new Page(R.drawable.page6, R.string.page6));
    }

    public Page getPage(int pageNumber) {
        if (pageNumber >= this.pages.size()) {
            pageNumber = 0;
        }
        return this.pages.get(pageNumber);
    }
}
