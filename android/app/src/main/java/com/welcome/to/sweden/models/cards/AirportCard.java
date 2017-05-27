package com.welcome.to.sweden.models.cards;

import com.welcome.to.sweden.models.AirportAlternative;
import com.welcome.to.sweden.models.cards.base.Card;

import java.util.List;

public class AirportCard extends Card {

    private List<AirportAlternative> alternatives;

    public AirportCard(List<AirportAlternative> alternatives) {
        this.alternatives = alternatives;
    }

    public List<AirportAlternative> getAlternatives() {
        return alternatives;
    }

}
