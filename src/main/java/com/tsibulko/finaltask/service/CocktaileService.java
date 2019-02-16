package com.tsibulko.finaltask.service;

//
//import java.sql.SQLException;
//import java.util.List;
//
//public class CocktaileService implements Service {
//    DAOFactory123 daoFactory = DAOFactory123.getInstance();
//
//    public void createNewCocktail(Cocktaile cocktaile) throws IllegalTypeDAOException, SQLException {
//        GenericDAO dao = daoFactory.getDao(DAOTypeEnum.COCKTAILE);
//        dao.create(cocktaile);
//    }
//
//    public void deleteCocktaile(int id) throws IllegalTypeDAOException, SQLException {
//        GenericDAO dao = daoFactory.getDao(DAOTypeEnum.COCKTAILE);
//        dao.deleteData(id);
//    }
//
//    public void uppdateCocktile(Cocktaile cocktaile) throws IllegalTypeDAOException, SQLException {
//        GenericDAO dao = daoFactory.getDao(DAOTypeEnum.COCKTAILE);
//        dao.updateData(cocktaile);
//    }
//
//    public List<Cocktaile> showCocktileList(){
//        return null;
//    }
//
//    public void setComment(){
//        //Сделать после ДАО для комментов
//    }
//
//    public void addIngredients(Cocktaile cocktaile , Ingredient ingredient){
//        cocktaile.getIngredients().put(ingredient.getName(),ingredient);
//    }
//
//
//    public void removeIngredients(Cocktaile cocktaile, Ingredient ingredient){
//        cocktaile.getIngredients().remove(ingredient.getName(),ingredient);
//    }
//
//
//    public Ingredient getIngredients(Cocktaile cocktaile, String name){
//        return cocktaile.getIngredients().get(name);
//    }
//}
