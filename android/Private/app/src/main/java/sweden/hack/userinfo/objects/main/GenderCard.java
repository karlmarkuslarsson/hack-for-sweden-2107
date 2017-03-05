package sweden.hack.userinfo.objects.main;

import sweden.hack.userinfo.objects.main.base.MainCard;

public class GenderCard extends MainCard {

    private Gender mGender;

    public enum Gender {WOMAN, MAN}

    public GenderCard(String personNumber) {
        try {
            int genderNumber = Integer.parseInt(
                    String.valueOf(personNumber.charAt(personNumber.length() - 2)));
            if (genderNumber % 2 == 0) {
                mGender = Gender.WOMAN;
            } else {
                mGender = Gender.MAN;
            }
        } catch (Exception ignore) {
            mGender = Gender.WOMAN;
        }
    }

    public Gender getGender() {
        return mGender;
    }

    @Override
    public int getViewType() {
        return TYPE_GENDER;
    }

}
