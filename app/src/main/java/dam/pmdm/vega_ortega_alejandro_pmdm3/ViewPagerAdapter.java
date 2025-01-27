package dam.pmdm.vega_ortega_alejandro_pmdm3;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import dam.pmdm.vega_ortega_alejandro_pmdm3.ui.ajustes.ajustesFragment;
import dam.pmdm.vega_ortega_alejandro_pmdm3.ui.capturados.capturadosFragment;
import dam.pmdm.vega_ortega_alejandro_pmdm3.ui.pokedex.PokedexFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new capturadosFragment();
            case 1:
                return new PokedexFragment();
            case 2:
                return new ajustesFragment();
            default:
                throw new IllegalStateException("Invalid position: " + position);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}