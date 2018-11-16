( function () {

    /**
     *
     * @param container
     * @return true all is not null
     * @return false some is null
     */
    var checkAllFieldIsNotNull = function( container ) {

        $.each( container.find( 'input' ), function () {
            if ( !$( this ).val() )
                return false;
            return true;
        } );

    };

    /**
     *
     * @param object
     * @return true all is not null
     * @return false some is null
     */
    var checkAllObjectPropIsNotNull = function( obj ) {

        var isAllNotNullFlag = true;
        
        if ( Object.keys(obj).length == 0 ) {
            isAllNotNullFlag = false;
        }
        else {
            Object.keys( obj ).forEach( function ( key, value ) {

                if ( !obj[key]) {
                    isAllNotNullFlag = false;
                }
            } );
        }

        return isAllNotNullFlag;

    }

    window.checkAllFieldIsNotNull = checkAllFieldIsNotNull;
    window.checkAllObjectPropIsNotNull = checkAllObjectPropIsNotNull;

} )();